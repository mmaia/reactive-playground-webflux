package io.maia.reactorplayground.services.clients;

import io.maia.reactorplayground.sharedkernel.dto.Car;
import reactor.core.publisher.Mono;

import java.util.List;

public interface BrandClient {
  public Mono<List<Car>> search();
}
