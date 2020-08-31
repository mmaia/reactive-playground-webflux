package io.resona.reactorplayground.services;

import io.resona.reactorplayground.api.dto.Brand;
import io.resona.reactorplayground.api.dto.Car;
import io.resona.reactorplayground.util.CarFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class GMBrandService implements BrandService {

  private static final SecureRandom random = new SecureRandom();

  @Override
  public Mono<List<Car>> search() {
    log.info("GMBrandService.search()");

    List<Car> result = new ArrayList<>();

    for (int i = 0; i < carNames.size() * 3; i++) {
      Car car = CarFactory.fromBrandAndModel(carName(), brand());
      result.add(car);
    }

    return Flux.fromIterable(result).collectList();
  }

  @Override
  public Brand brand() {
    return Brand.GM;
  }

  @Override
  public String carName() {
    int nextInt = random.nextInt(carNames.size());
    return (carNames.get(nextInt));
  }

  private final static List<String> carNames = List.of("Bolt", "Camaro", "Silverado", "Trax", "Suburban", "Equinox", "Corvette", "Malibu", "Sonic", "Spark");

}
