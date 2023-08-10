package com.dev.fullstackdemo.domain;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.Optional;

/**
 * maintain use data
 * exported 'false' prevents /users endpoint from being visible from localhost:8080/api
 */
@RepositoryRestResource
@CrossOrigin(origins = {"http://localhost:3000"}, exposedHeaders = {"Access-Control-Allow-Origin", "Access-Control-Allow-Credentials"})
public interface CustomUserRepository extends CrudRepository<CustomUser, Long> {
    Optional<CustomUser> findById(Long id);

    Optional<CustomUser> findByUsername(String username);

    Optional<CustomUser> findByPassword(String password);
}
