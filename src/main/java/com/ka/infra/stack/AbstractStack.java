package com.ka.infra.stack;

import com.ka.infra.constant.ConfigKeys;
import java.util.Properties;
import software.amazon.awscdk.core.Construct;
import software.amazon.awscdk.core.Stack;
import software.amazon.awscdk.core.StackProps;
import software.amazon.awscdk.services.ec2.Vpc;
import software.amazon.awscdk.services.ec2.VpcLookupOptions;
import software.amazon.awscdk.services.ec2.VpcProps;

public abstract class AbstractStack extends Stack {

  protected Vpc vpc;
  protected Properties properties;

  protected abstract void init();

  protected AbstractStack(Construct scope, String id, StackProps props, Properties properties) {
    super(scope, id, props);
    this.properties = properties;
    initVpc(properties);
    init();
  }

  protected void initVpc(Properties properties) {
    String vpcId = properties.getProperty(ConfigKeys.KEY_VPC_ID);
    String foundVpcId = Vpc.fromLookup(this, vpcId,
        VpcLookupOptions.builder().vpcId(vpcId).isDefault(false).build()).getVpcId();

    if (!vpcId.equals(foundVpcId)) {
      int maxAvailabilityZone = Integer
          .parseInt(properties.getProperty(ConfigKeys.KEY_VPC_MAX_AVAILABILITY_ZONE));

      vpc = new Vpc(this, vpcId + "-vpc", VpcProps.builder().maxAzs(maxAvailabilityZone).build());
    }
  }

}
