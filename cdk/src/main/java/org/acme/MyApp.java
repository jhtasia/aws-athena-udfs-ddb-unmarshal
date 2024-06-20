package org.acme;

import software.amazon.awscdk.App;

public class MyApp {
  public static void main(final String[] args) {
    App app = new App();
    new MyStack(app, "AthenaUdfDdbMarshalStack");
    app.synth();
  }
}