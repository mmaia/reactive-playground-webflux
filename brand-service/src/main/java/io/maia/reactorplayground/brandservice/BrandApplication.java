package io.maia.reactorplayground.brandservice;

import io.maia.reactorplayground.brandservice.config.BrandServiceConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@EnableConfigurationProperties(BrandServiceConfig.class)
@SpringBootApplication
public class BrandApplication {
  public static void main(String[] args) {
    SpringApplication.run(BrandApplication.class, args);
  }
}
