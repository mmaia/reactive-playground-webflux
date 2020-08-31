package io.maia.reactorplayground.brandservice.api;

import io.maia.reactorplayground.brandservice.services.BrandService;
import io.maia.reactorplayground.brandservice.services.FiatBrandService;
import io.maia.reactorplayground.brandservice.services.FordBrandService;
import io.maia.reactorplayground.brandservice.services.GMBrandService;
import io.maia.reactorplayground.brandservice.services.VolkswagenBrandService;
import io.maia.reactorplayground.sharedkernel.dto.Car;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class BrandApi {

  private final FiatBrandService fiatBrandService;
  private final FordBrandService fordBrandService;
  private final GMBrandService gMBrandService;
  private final VolkswagenBrandService volkswagenBrandService;

  @GetMapping("/ford")
  public Mono<List<Car>> findFords() {
    return fordBrandService.search();
  }

  @GetMapping("/fiat")
  public Mono<List<Car>> findFiats() {
    return fiatBrandService.search();
  }

  @GetMapping("/gm")
  public Mono<List<Car>> findGms() {
    return gMBrandService.search();
  }

  @GetMapping("/volkswagen")
  public Mono<List<Car>> findVolks() {
    return volkswagenBrandService.search();
  }
}
