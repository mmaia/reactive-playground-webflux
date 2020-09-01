package io.maia.reactorplayground.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix="brand-client")
public class BrandClientConfig {

  private int maxWaitForResponseInSeconds = 5;

}
