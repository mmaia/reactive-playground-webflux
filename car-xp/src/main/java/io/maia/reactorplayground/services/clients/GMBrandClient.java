package io.maia.reactorplayground.services.clients;

import io.maia.reactorplayground.config.BrandClientConfig;
import io.maia.reactorplayground.sharedkernel.dto.Car;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.security.SecureRandom;
import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Slf4j
@Service
public class GMBrandClient implements BrandClient {

  private final WebClient webClient;
  private final BrandClientConfig brandClientConfig;
  private static final SecureRandom random = new SecureRandom();

  public GMBrandClient(WebClient.Builder webClientBuilder, BrandClientConfig brandClientConfig) {
    this.webClient = webClientBuilder.baseUrl(brandClientConfig.getBrandServiceUrl()).build();
    this.brandClientConfig = brandClientConfig;
  }

  @Override
  public Mono<List<Car>> search() {
    log.info("GMBrandClient.search");
    return this.webClient.get().uri("/gm").retrieve().bodyToFlux(Car.class).collectList()
      .timeout(Duration.of(waitTime(), ChronoUnit.MILLIS));
  }

  private int waitTime() {
    int result = brandClientConfig.getGm().getMaxWaitForResponseInMillis();
    if(brandClientConfig.getGm().isRandom()) {
      result = random.nextInt(result);
    }
    return result;
  }
}
