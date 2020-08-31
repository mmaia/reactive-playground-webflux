package io.resona.reactorplayground.services;

import io.resona.reactorplayground.api.dto.Car;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CarService {

  private final List<BrandService> brandServices;

  public List<Car> searchAllWithBlock() {
    List<Car> allCars = Flux.fromIterable(brandServices)
      .flatMap(BrandService::search)
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
