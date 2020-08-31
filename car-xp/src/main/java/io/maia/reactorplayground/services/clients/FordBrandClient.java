package io.maia.reactorplayground.services.clients;

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
public class FordBrandClient implements BrandClient{

  private final WebClient webClient;

  public FordBrandClient(WebClient.Builder webClientBuilder) {
    this.webClient = webClientBuilder.baseUrl("http://localhost:8180").build();
  }


  @Override
  public Mono<List<Car>> search() {
    log.info("FordBrandClient.search");
    return this.webClient.get().uri("/ford").retrieve().bodyToFlux(Car.class).collectList().timeout(Duration.of(15, ChronoUnit.SECONDS));
  }
}
