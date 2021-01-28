package com.ka.infra.service.lambda;

import com.ka.infra.constant.ConfigKeys;
import com.ka.infra.service.AbstractService;
import java.util.Properties;
import software.amazon.awscdk.core.Construct;
import software.amazon.awscdk.core.Duration;
import software.amazon.awscdk.core.RemovalPolicy;
import software.amazon.awscdk.services.dynamodb.Attribute;
import software.amazon.awscdk.services.dynamodb.AttributeType;
import software.amazon.awscdk.services.dynamodb.Table;
import software.amazon.awscdk.services.dynamodb.TableProps;
import software.amazon.awscdk.services.ec2.Vpc;
import software.amazon.awscdk.services.ecr.IRepository;
import software.amazon.awscdk.services.ecr.Repository;
import software.amazon.awscdk.services.lambda.Code;
import software.amazon.awscdk.services.lambda.Function;
import software.amazon.awscdk.services.lambda.Handler;
import software.amazon.awscdk.services.lambda.Runtime;
import software.amazon.awscdk.services.s3.BlockPublicAccess;
import software.amazon.awscdk.services.s3.Bucket;
import software.amazon.awscdk.services.s3.EventType;
import software.amazon.awscdk.services.s3.notifications.LambdaDestination;

public class FileImportService extends AbstractService {

  private String bucketName;
  private String tableName;
  private String partitionKeyName;
  private String lambdaHandlerName;

  public FileImportService(Construct scope, Vpc vpc, Properties properties) {
    super(scope, vpc, properties);
  }

  @Override
  protected void loadProperties() {
    bucketName = properties.getProperty(ConfigKeys.KEY_FILE_IMPORT_SERVICE_S3_BUCKET_NAME);
    tableName = properties.getProperty(ConfigKeys.KEY_FILE_IMPORT_SERVICE_DYNAMODB_TABLE_NAME);
    partitionKeyName =
        properties.getProperty(ConfigKeys.KEY_FILE_IMPORT_SERVICE_DYNAMODB_PARTITION_KEY_NAME);
    lambdaHandlerName =
        properties.getProperty(ConfigKeys.KEY_FILE_IMPORT_SERVICE_LAMBDA_HANDLER_NAME);
  }

  @Override
  protected void init() {
    final Bucket bucket =
        Bucket.Builder.create(scope, bucketName + "-id").versioned(true).publicReadAccess(false)
            .blockPublicAccess(
                BlockPublicAccess.BLOCK_ALL)
            .removalPolicy(RemovalPolicy.DESTROY).build();

    IRepository repository = Repository.fromRepositoryName(scope, "FileImportRepository",
        "file-import-service");

    Function function = Function.Builder.create(scope, lambdaHandlerName)
        .runtime(Runtime.FROM_IMAGE)
        .timeout(Duration.seconds(30))
        .memorySize(1024)
        .code(Code.fromEcrImage(repository))
        .handler(Handler.FROM_IMAGE)
        .build();

    LambdaDestination lambdaDestination = new LambdaDestination(function);

    bucket.addEventNotification(EventType.OBJECT_CREATED_PUT, lambdaDestination);
    bucket.grantReadWrite(function);

    Attribute partitionKey = Attribute.builder()
        .name(partitionKeyName)
        .type(AttributeType.STRING)
        .build();

    TableProps tableProps = TableProps.builder()
        .tableName(tableName)
        .partitionKey(partitionKey)
        .removalPolicy(RemovalPolicy.DESTROY)
        .build();

    Table table = new Table(scope, tableName, tableProps);
    table.grantReadWriteData(function);
  }

}
