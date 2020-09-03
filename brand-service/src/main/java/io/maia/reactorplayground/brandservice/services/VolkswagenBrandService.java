package io.maia.reactorplayground.brandservice.services;

import io.maia.reactorplayground.brandservice.config.BrandServiceConfig;
import io.maia.reactorplayground.sharedkernel.dto.Brand;
import io.maia.reactorplayground.sharedkernel.dto.Car;
import io.maia.reactorplayground.sharedkernel.util.CarFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class VolkswagenBrandService implements BrandService {

  private static final SecureRandom random = new SecureRandom();

  private final BrandServiceConfig brandServiceConfig;

  @PostConstruct
  public void logProps() {
    log.info("\nVolkswagen, \nCars per search: {}, \nRandom: {}, \nResponse(Max) time in millis: {}",
      brandServiceConfig.getVolkswagen().getMaxCarsPerSearch(),
      brandServiceConfig.getVolkswagen().isRandom(),
      brandServiceConfig.getVolkswagen().getMaxResponseTimeInMillis());
  }

  @Override
  public List<Car> search() {
    log.info("VolkswagenBrandService.search()");
    List<Car> result = new ArrayList<>();

    delayResponse();

    for (int i = 0; i < brandServiceConfig.getVolkswagen().getMaxCarsPerSearch(); i++) {
      Car car = CarFactory.fromBrandAndModel(carName(), brand());
      result.add(car);
    }

    return result;
  }

  private void delayResponse() {
    int delay = brandServiceConfig.getVolkswagen().getMaxResponseTimeInMillis();
    if(brandServiceConfig.getVolkswagen().isRandom()) {
      delay = random.nextInt(delay);
    }
    try {
      Thread.sleep(delay);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
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
