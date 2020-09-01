package io.maia.reactorplayground.services.clients;

import io.maia.reactorplayground.config.BrandClientConfig;
import io.maia.reactorplayground.sharedkernel.dto.Car;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Slf4j
@Service
public class VolkswagenBrandClient implements BrandClient {

  private final WebClient webClient;
  private final BrandClientConfig brandClientConfig;

  public VolkswagenBrandClient(WebClient.Builder webClientBuilder, BrandClientConfig brandClientConfig) {
    this.webClient = webClientBuilder.baseUrl(brandClientConfig.getBrandServiceUrl()).build();
    this.brandClientConfig = brandClientConfig;
  }

  @Override
  public Mono<List<Car>> search() {
    log.info("VolkswagenBrandClient.search");
    return this.webClient.get().uri("/volkswagen").retrieve().bodyToFlux(Car.class).collectList()
      .timeout(Duration.of(brandClientConfig.getMaxWaitForResponseInSeconds(), ChronoUnit.SECONDS));
  }

}
