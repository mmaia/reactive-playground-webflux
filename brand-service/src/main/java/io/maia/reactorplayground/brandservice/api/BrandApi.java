package io.maia.reactorplayground.brandservice.api;

import io.maia.reactorplayground.brandservice.services.BrandService;
import io.maia.reactorplayground.brandservice.services.FiatBrandService;
import io.maia.reactorplayground.brandservice.services.FordBrandService;
import io.maia.reactorplayground.brandservice.services.GMBrandService;
import io.maia.reactorplayground.brandservice.services.VolkswagenBrandService;
import io.maia.reactorplayground.sharedkernel.dto.Car;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class BrandApi {

  private final FiatBrandService fiatBrandService;
  private final FordBrandService fordBrandService;
  private final GMBrandService gMBrandService;
  private final VolkswagenBrandService volkswagenBrandService;

  @GetMapping("/ford")
  public ResponseEntity<List<Car>> findFords() {
    List<Car> cars = fordBrandService.search();
    return ResponseEntity.ok(cars);
  }

  @GetMapping("/fiat")
  public ResponseEntity<List<Car>> findFiats() {
    return ResponseEntity.ok(fiatBrandService.search());
  }

  @GetMapping("/gm")
  public ResponseEntity<List<Car>> findGms() {
    return ResponseEntity.ok(gMBrandService.search());
  }

  @GetMapping("/volkswagen")
  public ResponseEntity<List<Car>> findVolks() {
    return ResponseEntity.ok(volkswagenBrandService.search());
  }
}
