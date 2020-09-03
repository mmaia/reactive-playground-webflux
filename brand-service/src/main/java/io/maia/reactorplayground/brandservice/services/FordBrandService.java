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
public class FordBrandService implements BrandService {

  private static final SecureRandom random = new SecureRandom();

  private final BrandServiceConfig brandServiceConfig;

  @Override
  public Mono<List<Car>> search() {
    log.info("FordBrandService.search()");

    List<Car> result = new ArrayList<>();

    delayResponse();

    for (int i = 0; i < brandServiceConfig.getFord().getMaxCarsPerSearch(); i++) {
      Car car = CarFactory.fromBrandAndModel(carName(), brand());
      result.add(car);
    }

    return Flux.fromIterable(result).collectList();
  }

  private void delayResponse() {
    int delay = brandServiceConfig.getFord().getMaxResponseTimeInMillis();
    if(brandServiceConfig.getFord().isRandom()) {
      delay = random.nextInt(delay);
    }
    try {
      Thread.sleep(delay);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  private Brand brand() {
    return Brand.FORD;
  }

  private String carName() {
    int nextInt = random.nextInt(carNames.size());
    return (carNames.get(nextInt));
  }

  private final static List<String> carNames = List.of("Ecosport", "Escape", "Bronco Sport", "Bronco", "Edge", "Explorer", "Fusion", "Mustang");

}
