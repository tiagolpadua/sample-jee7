package org.timsoft.api;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import io.swagger.jaxrs.config.BeanConfig;

@ApplicationPath("api")
public class ApplicationConfig extends Application {

  public ApplicationConfig() {
    BeanConfig beanConfig = new BeanConfig();
    beanConfig.setTitle("Sample JEE7 API");
    beanConfig.setVersion("1.0.0");
    beanConfig.setSchemes(new String[] {"http"});
    beanConfig.setHost("localhost:7001");
    beanConfig.setBasePath("/sample-jee7/api");
    beanConfig.setResourcePackage("org.timsoft.api");
    beanConfig.setScan(true);
  }
}
