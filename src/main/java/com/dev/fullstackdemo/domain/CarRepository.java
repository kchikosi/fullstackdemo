package com.dev.fullstackdemo.domain;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;
import java.util.Optional;

@RepositoryRestResource
@CrossOrigin
public interface CarRepository extends CrudRepository<Car, Long> {
    Optional<List<Car>> findById(@Param("id") int id);

    Optional<List<Car>> findByBrand(@Param("brand") String brand);

    Optional<List<Car>> findByColor(@Param("color") String color);
}
