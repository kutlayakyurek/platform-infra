package com.ka.infra;

import com.ka.infra.constant.ConfigKeys;
import com.ka.infra.service.ecs.CalculationService;
import com.ka.infra.service.lambda.FileImportService;
import java.util.Properties;
import software.amazon.awscdk.core.Construct;
import software.amazon.awscdk.core.Stack;
import software.amazon.awscdk.core.StackProps;
import software.amazon.awscdk.services.ec2.Vpc;
import software.amazon.awscdk.services.ec2.VpcProps;

public class PlatformInfraStack extends Stack {

  private final Properties properties;
  private Vpc vpc;

  public PlatformInfraStack(Construct scope, String id, StackProps props, Properties properties) {
    super(scope, id, props);
    this.properties = properties;
    initVpc();
    initServices();
  }

  protected void initVpc() {
    String vpcId = properties.getProperty(ConfigKeys.KEY_VPC_ID);

    int maxAvailabilityZone = Integer
        .parseInt(properties.getProperty(ConfigKeys.KEY_VPC_MAX_AVAILABILITY_ZONE));

    vpc = new Vpc(this, vpcId + "-vpc", VpcProps.builder().maxAzs(maxAvailabilityZone).build());
  }

  private void initServices() {
    new CalculationService(this, vpc, properties);
    new FileImportService(this, vpc, properties);
  }

}
