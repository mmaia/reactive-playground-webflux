package io.maia.reactorplayground.brandservice.services;

import io.maia.reactorplayground.brandservice.config.BrandServiceConfig;
import io.maia.reactorplayground.sharedkernel.dto.Brand;
import io.maia.reactorplayground.sharedkernel.dto.Car;
import io.maia.reactorplayground.sharedkernel.util.CarFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class FiatBrandService implements BrandService {

  private static final SecureRandom random = new SecureRandom();

  private final BrandServiceConfig brandServiceConfig;

  @Override
  public Mono<List<Car>> search() {
    log.info("FiatBrandService.search()");

    List<Car> result = new ArrayList<>();


    try {
      Thread.sleep(random.nextInt(brandServiceConfig.getMaxResponseTimeInMillis()));
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    for (int i = 0; i < brandServiceConfig.getMaxCarsPerSearch() ; i++) {
      Car car = CarFactory.fromBrandAndModel(carName(), brand());
      result.add(car);
    }

    return Flux.fromIterable(result).collectList();

  }

  private Brand brand() {
    return Brand.FIAT;
  }

  private String carName() {
    int nextInt = random.nextInt(carNames.size());
    return (carNames.get(nextInt));
  }

  private final static List<String> carNames = List.of("500", "500C RANGE", "NEW 500", "500x", "PANDA", "TIPO");
}
