package com.ka.infra.constant;

public interface ConfigKeys {

  // Common
  String KEY_ACCOUNT = "account";
  String KEY_REGION = "region";
  String KEY_VPC_ID = "vpc.id";
  String KEY_VPC_MAX_AVAILABILITY_ZONE = "vpc.max.az";
  String KEY_CLOUDFORMATION_STACK_NAME = "cloudformation.stack.name";

  // Calculation Service
  String KEY_CALCULATION_SERVICE_ECS_CLUSTER_NAME = "calculation.service.ecs.cluster.name";
  String KEY_CALCULATION_SERVICE_ECS_SERVICE_NAME = "calculation.service.ecs.service.name";
  String KEY_CALCULATION_SERVICE_ECS_REGISTRY = "calculation.service.ecs.calculation.service.registry";
  String KEY_CALCULATION_SERVICE_ECS_CONTAINER_PORT = "calculation.service.ecs.container.port";

  // File Import Service
  String KEY_FILE_IMPORT_SERVICE_S3_BUCKET_NAME = "file.import.service.s3.bucket.name";
  String KEY_FILE_IMPORT_SERVICE_DYNAMODB_TABLE_NAME = "file.import.service.dynamodb.table.name";
  String KEY_FILE_IMPORT_SERVICE_DYNAMODB_PARTITION_KEY_NAME = "file.import.service.dynamodb.partition.key.name";
  String KEY_FILE_IMPORT_SERVICE_LAMBDA_HANDLER_NAME = "file.import.service.lambda.handler.name";

}
