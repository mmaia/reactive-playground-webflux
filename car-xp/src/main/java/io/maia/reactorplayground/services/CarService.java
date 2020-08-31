package io.maia.reactorplayground.services;

import io.maia.reactorplayground.services.clients.BrandClient;
import io.maia.reactorplayground.sharedkernel.dto.Car;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CarService {

  private final List<BrandClient> brandClients;

  public List<Car> searchAllWithBlock() {

    List<Car> allCars = Flux.fromIterable(brandClients)
      .flatMap(BrandClient::search)
      .onErrorReturn(Collections.emptyList())
      .flatMap(Flux::fromIterable)
      .collectList()
      .block();

    return allCars;

  }

  public List<Car> searchAllWithMerge() {
    return Collections.emptyList();
  }

}
