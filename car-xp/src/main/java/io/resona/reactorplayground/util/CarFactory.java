package io.resona.reactorplayground.util;

import io.resona.reactorplayground.api.dto.Brand;
import io.resona.reactorplayground.api.dto.Car;
import io.resona.reactorplayground.api.dto.Color;

import java.security.SecureRandom;
import java.util.UUID;

public class CarFactory {

  private static final SecureRandom random = new SecureRandom();

  public static  synchronized Car randomCar() {
    Car car = new Car();
    car.setName(UUID.randomUUID().toString()); // for now completely random
    car.setColor(randomEnum(Color.class));
    car.setBrand(randomEnum(Brand.class));
    return car;
  }

  public static synchronized Car fromBrandAndModel(String carName, Brand brand) {
    Car car = new Car();
    car.setName(carName);
    car.setBrand(brand);
    car.setColor(randomEnum(Color.class));
    return car;
  }

  public static <T extends Enum<?>> T randomEnum(Class<T> clazz){
    int x = random.nextInt(clazz.getEnumConstants().length);
    return clazz.getEnumConstants()[x];
  }

}
