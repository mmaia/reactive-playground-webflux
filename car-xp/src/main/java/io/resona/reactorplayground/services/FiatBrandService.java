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
public class FiatBrandService implements BrandService {

  private static final SecureRandom random = new SecureRandom();

  @Override
  public Mono<List<Car>> search() {
    log.info("FiatBrandService.search()");

    List<Car> result = new ArrayList<>();

    try {
      Thread.sleep(5_000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    for (int i = 0; i < carNames.size() * 2; i++) {
      Car car = CarFactory.fromBrandAndModel(carName(), brand());
      result.add(car);
    }

    return Flux.fromIterable(result).collectList();

  }

  @Override
  public Brand brand() {
    return Brand.FIAT;
  }

  @Override
  public String carName() {
    int nextInt = random.nextInt(carNames.size());
    return (carNames.get(nextInt));
  }

  private final static List<String> carNames = List.of("500", "500C RANGE", "NEW 500", "500x", "PANDA", "TIPO");
}
