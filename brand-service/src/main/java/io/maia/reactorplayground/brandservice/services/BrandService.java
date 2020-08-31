package io.maia.reactorplayground.brandservice.services;

import io.maia.reactorplayground.sharedkernel.dto.Car;
import reactor.core.publisher.Mono;

import java.util.List;

public interface BrandService {

  Mono<List<Car>> search();

}
