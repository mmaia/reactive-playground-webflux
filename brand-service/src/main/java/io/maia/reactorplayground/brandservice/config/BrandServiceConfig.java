package io.maia.reactorplayground.brandservice.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix="brand")
public class BrandServiceConfig {
  private int maxResponseTimeInMillis; // time in millis
  private int maxCarsPerSearch;
}
