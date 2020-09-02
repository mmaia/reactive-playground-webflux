package io.maia.reactorplayground.config;

import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TomcatConfig {

  // enable tomcat JMX in sb 2.2
  @Bean
  public WebServerFactoryCustomizer<TomcatServletWebServerFactory> activateTomcatMBeanServer() {
    return (factory) -> factory.setDisableMBeanRegistry(false);
  }

}
