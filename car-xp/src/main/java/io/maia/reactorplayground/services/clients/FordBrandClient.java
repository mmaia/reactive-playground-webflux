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
public class FordBrandClient implements BrandClient{

  private final WebClient webClient;
  private final BrandClientConfig brandClientConfig;
  private static final SecureRandom random = new SecureRandom();

  public FordBrandClient(WebClient.Builder webClientBuilder, BrandClientConfig brandClientConfig) {
    this.webClient = webClientBuilder.baseUrl(brandClientConfig.getBrandServiceUrl()).build();
    this.brandClientConfig = brandClientConfig;

    log.info("\nFord: \nRandom: {}, \nMax Wait for Response {}",
      brandClientConfig.getFord().isRandom(),
      brandClientConfig.getFord().getMaxWaitForResponseInMillis());
  }

  @Override
  public Mono<List<Car>> search() {
    log.debug("FordBrandClient.search");
    return this.webClient.get().uri("/ford").retrieve().bodyToFlux(Car.class).collectList()
      .timeout(Duration.of(waitTime(), ChronoUnit.MILLIS))
      .doOnError((throwable) -> log.error("Timed out Ford search", throwable));
  }

  private int waitTime() {
    int result = brandClientConfig.getFord().getMaxWaitForResponseInMillis();
    if(brandClientConfig.getFord().isRandom()) {
      result = random.nextInt(result);
    }
    return result;
  }
}
