package io.maia.reactorplayground.brandservice.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix="brand")
public class BrandServiceConfig {
  private Ford ford;
  private GM gm;
  private Volkswagen volkswagen;
  private Fiat fiat;

  @Data
  public class Ford {
    private boolean random;
    private int maxResponseTimeInMillis;
    private int maxCarsPerSearch;
  }

  @Data
  public class GM {
    private boolean random;
    private int maxResponseTimeInMillis;
    private int maxCarsPerSearch;
  }

  @Data
  public class Volkswagen {
    private boolean random;
    private int maxResponseTimeInMillis;
    private int maxCarsPerSearch;
  }

  @Data
  public class Fiat {
    private boolean random;
    private int maxResponseTimeInMillis;
    private int maxCarsPerSearch;
  }

}
