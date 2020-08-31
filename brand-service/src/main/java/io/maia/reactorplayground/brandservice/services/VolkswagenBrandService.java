package io.maia.reactorplayground.brandservice.services;

import io.maia.reactorplayground.sharedkernel.dto.Brand;
import io.maia.reactorplayground.sharedkernel.dto.Car;
import io.maia.reactorplayground.sharedkernel.util.CarFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class VolkswagenBrandService implements BrandService {

  private static final SecureRandom random = new SecureRandom();

  @Override
  public Mono<List<Car>> search() {
    log.info("VolkswagenBrandService.search()");
    List<Car> result = new ArrayList<>();

    try {
      Thread.sleep(1_000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    for (int i = 0; i < carNames.size() * 3; i++) {
      Car car = CarFactory.fromBrandAndModel(carName(), brand());
      result.add(car);
    }

    return Flux.fromIterable(result).collectList();
  }


  private Brand brand() {
    return Brand.VOLKSWAGEN;
  }

  private String carName() {
    int nextInt = random.nextInt(carNames.size());
    return (carNames.get(nextInt));
  }

  private final static List<String> carNames = List.of("Golf", "Polo", "Passat", "T-Roc", "Touran", "up", "T-Cross", "e-up", "Tiguan", "Passat Variant", "Arteon", "Touareg");
}
