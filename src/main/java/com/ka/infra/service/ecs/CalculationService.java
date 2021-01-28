package com.ka.infra.service.ecs;

import com.ka.infra.constant.ConfigKeys;
import java.util.Properties;
import software.amazon.awscdk.core.Construct;
import software.amazon.awscdk.services.ec2.Vpc;
import software.amazon.awscdk.services.ecs.Cluster;
import software.amazon.awscdk.services.ecs.ClusterProps;
import software.amazon.awscdk.services.ecs.ContainerImage;
import software.amazon.awscdk.services.ecs.patterns.ApplicationLoadBalancedFargateService;
import software.amazon.awscdk.services.ecs.patterns.ApplicationLoadBalancedFargateServiceProps;
import software.amazon.awscdk.services.ecs.patterns.ApplicationLoadBalancedTaskImageOptions;

public class CalculationService extends AbstractEcsService {

  public CalculationService(Construct scope, Vpc vpc, Properties properties) {
    super(scope, vpc, properties);
  }

  @Override
  protected void loadProperties() {
    serviceName = properties.getProperty(ConfigKeys.KEY_CALCULATION_SERVICE_ECS_SERVICE_NAME);
    clusterName = properties.getProperty(ConfigKeys.KEY_CALCULATION_SERVICE_ECS_CLUSTER_NAME);
    registry = properties.getProperty(ConfigKeys.KEY_CALCULATION_SERVICE_ECS_REGISTRY);
    containerPort = Integer
        .parseInt(properties.getProperty(ConfigKeys.KEY_CALCULATION_SERVICE_ECS_CONTAINER_PORT));
  }

  @Override
  protected void init() {
    Cluster cluster = new Cluster(scope, clusterName, ClusterProps.builder().vpc(vpc).build());

    new ApplicationLoadBalancedFargateService(
        scope,
        serviceName,
        ApplicationLoadBalancedFargateServiceProps.builder()
            .cluster(cluster)
            .serviceName(serviceName)
            .taskImageOptions(ApplicationLoadBalancedTaskImageOptions.builder()
                .image(ContainerImage.fromRegistry(registry))
                .containerPort(containerPort)
                .build())
            .build());
  }

}
