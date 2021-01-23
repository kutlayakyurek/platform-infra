package com.myorg;

import software.amazon.awscdk.core.Construct;
import software.amazon.awscdk.core.Stack;
import software.amazon.awscdk.core.StackProps;

public class PlatformInfraStack extends Stack {
    public PlatformInfraStack(final Construct scope, final String id) {
        this(scope, id, null);
    }

    public PlatformInfraStack(final Construct scope, final String id, final StackProps props) {
        super(scope, id, props);

        // The code that defines your stack goes here
    }
}
