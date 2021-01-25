package com.ka.infra.constant;

public interface ConfigKeys {

  String KEY_ACCOUNT = "account";
  String KEY_REGION = "region";
  String KEY_VPC_ID = "vpc.id";
  String KEY_VPC_MAX_AVAILABILITY_ZONE = "vpc.max.az";

  String KEY_ECS_CLUSTER_NAME = "ecs.cluster.name";
  String KEY_ECS_CALCULATION_SERVICE_NAME = "ecs.calculation.service.name";
  String KEY_ECS_CALCULATION_SERVICE_REGISTRY = "ecs.calculation.service.registry";
  String KEY_ECS_CALCULATION_SERVICE_CONTAINER_PORT = "ecs.calculation.service.container.port";

  String KEY_CLOUDFORMATION_STACK_NAME = "stack.name";

}
