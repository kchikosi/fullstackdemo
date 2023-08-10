package com.dev.fullstackdemo.domain;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.Optional;

@RepositoryRestResource
@CrossOrigin(origins = {"http://localhost:3000"}, exposedHeaders = {"Access-Control-Allow-Origin", "Access-Control-Allow-Credentials"})
public interface OwnerRepository extends CrudRepository<Owner, Long> {

    Optional<Owner> findById(@Param("id") Long id);

    Optional<Owner> findByFirstName(@Param("firstName") String firstName);

    Optional<Owner> findByLastName(@Param("lastName") String lastName);
}
