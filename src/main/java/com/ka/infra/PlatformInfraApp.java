package com.ka.infra;

import com.ka.infra.constant.ConfigKeys;
import com.ka.infra.stack.FargateServiceStack;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import software.amazon.awscdk.core.App;
import software.amazon.awscdk.core.Environment;
import software.amazon.awscdk.core.StackProps;

public class PlatformInfraApp {

  private static final String PROPERTIES_FILE = "config.properties";

  public static void main(final String[] args) {
    Properties properties = loadConfiguration();
    App app = new App();
    initStacks(app, properties);
  }

  private static void initStacks(App app, Properties properties) {
    final String account = properties.getProperty(ConfigKeys.KEY_ACCOUNT);
    final String region = properties.getProperty(ConfigKeys.KEY_REGION);
    final StackProps stackProperties =
        StackProps.builder().env(Environment.builder().account(account).region(region).build())
            .build();

    new FargateServiceStack(app, properties.getProperty(ConfigKeys.KEY_CLOUDFORMATION_STACK_NAME)
        , stackProperties, properties);
  }

  private static Properties loadConfiguration() {
    Properties properties = new Properties();

    try (InputStream input = PlatformInfraApp.class.getClassLoader()
        .getResourceAsStream(PROPERTIES_FILE)) {

      if (input == null) {
        System.out.println("Unable to find " + PROPERTIES_FILE);
        return properties;
      }

      properties.load(input);
    } catch (IOException ex) {
      ex.printStackTrace();
    }

    return properties;
  }

}
