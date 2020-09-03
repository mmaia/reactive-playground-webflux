package io.maia.reactorplayground.brandservice.services;

import io.maia.reactorplayground.sharedkernel.dto.Car;

import java.util.List;

public interface BrandService {

  List<Car> search();

}
