package io.resona.reactorplayground.api;

import io.resona.reactorplayground.api.dto.Car;
import io.resona.reactorplayground.services.CarService;
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
    log.info("Entering CarAPI.search");
    return ResponseEntity.ok(carService.searchAllWithBlock());
  }
}
