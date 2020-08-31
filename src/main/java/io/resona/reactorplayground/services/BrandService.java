package io.resona.reactorplayground.services;

import io.resona.reactorplayground.api.dto.Brand;
import io.resona.reactorplayground.api.dto.Car;
import reactor.core.publisher.Mono;

import java.util.List;

public interface BrandService {

  public Mono<List<Car>> search();

  public Brand brand();

  public String carName();

}
