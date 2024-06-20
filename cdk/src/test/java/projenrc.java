import io.github.cdklabs.projen.awscdk.AwsCdkJavaApp;
import io.github.cdklabs.projen.awscdk.AwsCdkJavaAppOptions;

public class projenrc {
    public static void main(String[] args) {
        AwsCdkJavaApp project = new AwsCdkJavaApp(AwsCdkJavaAppOptions.builder()
            .artifactId("my-app")
            .cdkVersion("2.1.0")
            .groupId("org.acme")
            .mainClass("org.acme.MyApp")
            .name("cdk")
            .version("0.1.0")
            .github(false)
            .build());
        project.synth();
    }
}