package io.maia.reactorplayground.services;

import io.maia.reactorplayground.config.BrandClientConfig;
import io.maia.reactorplayground.services.clients.BrandClient;
import io.maia.reactorplayground.sharedkernel.dto.Car;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CarService {

  private final List<BrandClient> brandClients;

  private final BrandClientConfig brandClientConfig;

  public List<Car> searchAllWithBlock() {

    List<Car> allCars = Flux.fromIterable(brandClients)
      .flatMap(BrandClient::search)
      .onErrorReturn(Collections.emptyList())
      .flatMap(Flux::fromIterable)
      .collectList()
      .timeout(Duration.of(brandClientConfig.getMaxWaitForFullProcessingInSeconds(), ChronoUnit.SECONDS))
      .block();

    return allCars;

  }

  public List<Car> searchAllWithMerge() {
    return Collections.emptyList();
  }

}
