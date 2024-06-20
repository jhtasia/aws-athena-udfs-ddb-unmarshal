package org.acme;

import java.util.Arrays;
import java.util.List;

import software.amazon.awscdk.App;
import software.amazon.awscdk.Stack;

import software.amazon.awscdk.BundlingOptions;
import software.constructs.Construct;
import software.amazon.awscdk.DockerVolume;
import software.amazon.awscdk.Duration;
import software.amazon.awscdk.Stack;
import software.amazon.awscdk.StackProps;
import software.amazon.awscdk.services.lambda.Code;
import software.amazon.awscdk.services.lambda.Function;
import software.amazon.awscdk.services.lambda.FunctionProps;
import software.amazon.awscdk.services.lambda.Runtime;
import software.amazon.awscdk.services.lambda.Version;
import software.amazon.awscdk.services.logs.RetentionDays;
import software.amazon.awscdk.services.s3.assets.AssetOptions;

import static java.util.Collections.singletonList;
import static software.amazon.awscdk.BundlingOutput.ARCHIVED;

public class MyStack extends Stack {

  public MyStack(final App parent, final String id) {
    this(parent, id, null);
  }

  public MyStack(final Construct parent, final String id, final StackProps props) {
    super(parent, id, props);

    List<String> packagingInstructions = Arrays.asList(
      "/bin/sh",
      "-c",
      "mvn clean install " +
      "&& cp /asset-input/target/aws-athena-udfs-ddb-unmarshal-1.0-SNAPSHOT.jar /asset-output/"
    );

    BundlingOptions.Builder builderOptions = BundlingOptions.builder()
      .command(packagingInstructions)
      .image(Runtime.JAVA_11.getBundlingImage())
      .volumes(singletonList(
        // Mount local .m2 repo to avoid download all the dependencies again inside the container
        DockerVolume.builder()
          .hostPath(System.getProperty("user.home") + "/.m2/")
          .containerPath("/root/.m2/")
          .build()
      ))
      .user("root")
      .outputType(ARCHIVED);

    Function function = new Function(this, "ddbUnmarshalCS", FunctionProps.builder()
      .runtime(Runtime.JAVA_11)
      .code(Code.fromAsset("../udf/", AssetOptions.builder()
              .bundling(builderOptions
                      .command(packagingInstructions)
                      .build())
              .build()))
      .handler("com.johnsonfitness.DdbUnmarshal")
      .functionName("DdbUnmarshalCS")
      .memorySize(1024)
      .timeout(Duration.seconds(10))
      .logRetention(RetentionDays.ONE_WEEK)
      .build());

    Version version = Version.Builder.create(this, "version")
      .lambda(function)
      .build(); 

  }
}