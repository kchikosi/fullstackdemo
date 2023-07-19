package com.dev.fullstackdemo.domain;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource
public interface CarRepository extends CrudRepository<Car, Long> {
    // find by brand
    List<Car> findByBrand(@Param("brand") String brand);

    // find by color
    List<Car> findByColor(@Param("color") String color);
}
