package com.ka.infra.service.ecs;

import com.ka.infra.service.AbstractService;
import java.util.Properties;
import software.amazon.awscdk.core.Construct;
import software.amazon.awscdk.services.ec2.Vpc;

public abstract class AbstractEcsService extends AbstractService {

  protected String serviceName;
  protected String clusterName;
  protected String registry;
  protected int containerPort;

  public AbstractEcsService(Construct scope, Vpc vpc, Properties properties) {
    super(scope, vpc, properties);
  }

}
