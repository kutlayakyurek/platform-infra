package com.myorg;

import software.amazon.awscdk.core.App;

import java.util.Arrays;

public class PlatformInfraApp {
    public static void main(final String[] args) {
        App app = new App();

        new PlatformInfraStack(app, "PlatformInfraStack");

        app.synth();
    }
}
