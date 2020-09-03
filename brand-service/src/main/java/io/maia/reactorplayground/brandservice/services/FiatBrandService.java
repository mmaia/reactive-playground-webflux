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

import javax.annotation.PostConstruct;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class FiatBrandService implements BrandService {

  private static final SecureRandom random = new SecureRandom();

  private final BrandServiceConfig brandServiceConfig;

  @PostConstruct
  public void logProps() {
    log.info("\nFiat, \nCars per search: {}, \nRandom: {}, \nResponse(Max) time in millis: {}",
      brandServiceConfig.getFiat().getMaxCarsPerSearch(),
      brandServiceConfig.getFiat().isRandom(),
      brandServiceConfig.getFiat().getMaxResponseTimeInMillis());
  }

  @Override
  public Mono<List<Car>> search() {
    log.info("FiatBrandService.search()");

    List<Car> result = new ArrayList<>();

    delayResponse();

    for (int i = 0; i < brandServiceConfig.getFiat().getMaxCarsPerSearch() ; i++) {
      Car car = CarFactory.fromBrandAndModel(carName(), brand());
      result.add(car);
    }

    return Flux.fromIterable(result).collectList();

  }

  private void delayResponse() {
    int delay = brandServiceConfig.getFiat().getMaxResponseTimeInMillis();
    if(brandServiceConfig.getFiat().isRandom()) {
      delay = random.nextInt(delay);
    }
    try {
      Thread.sleep(delay);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
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
