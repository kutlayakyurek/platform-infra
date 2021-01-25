package com.ka.infra.service;

import java.util.Properties;
import software.amazon.awscdk.core.Construct;
import software.amazon.awscdk.services.ec2.Vpc;

public abstract class AbstractService {

  protected Vpc vpc;
  protected Properties properties;
  protected Construct scope;

  protected abstract void loadProperties();

  protected abstract void init();

  public AbstractService(Construct scope, Vpc vpc, Properties properties) {
    this.vpc = vpc;
    this.properties = properties;
    this.scope = scope;
    loadProperties();
    init();
  }

}
