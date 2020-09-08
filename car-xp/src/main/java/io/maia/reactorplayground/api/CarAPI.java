package io.maia.reactorplayground.api;

import io.maia.reactorplayground.services.CarService;
import io.maia.reactorplayground.sharedkernel.dto.Car;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RequiredArgsConstructor
@RestController
@Slf4j
public class CarAPI {

  private final CarService carService;

  @GetMapping
  public ResponseEntity<List<Car>> search() {
    log.debug("Entering CarAPI.search");
    return ResponseEntity.ok(carService.searchAllWithBlock());
  }
}
