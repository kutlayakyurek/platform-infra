package com.ka.infra.stack;

import com.ka.infra.service.ecs.CalculationService;
import java.util.Properties;
import software.amazon.awscdk.core.Construct;
import software.amazon.awscdk.core.StackProps;

public class FargateServiceStack extends AbstractStack {

  public FargateServiceStack(Construct scope, String id, StackProps props, Properties properties) {
    super(scope, id, props, properties);
  }

  @Override
  protected void init() {
    new CalculationService(this, vpc, properties);
  }

}
