package io.maia.reactorplayground.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix="brand-client")
public class BrandClientConfig {

  private String brandServiceUrl;
  private int maxWaitForFullProcessingInMillis;

  private Ford ford;
  private GM gm;
  private Volkswagen volkswagen;
  private Fiat fiat;

  @Data
  public static class Ford {
    private boolean random;
    private int maxWaitForResponseInMillis;
  }

  @Data
  public static class GM {
    private boolean random;
    private int maxWaitForResponseInMillis;
  }

  @Data
  public static class Volkswagen {
    private boolean random;
    private int maxWaitForResponseInMillis;
  }

  @Data
  public static class Fiat {
    private boolean random;
    private int maxWaitForResponseInMillis;
  }

}
