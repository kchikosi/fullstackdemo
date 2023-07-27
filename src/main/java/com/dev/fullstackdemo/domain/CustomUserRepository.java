package com.dev.fullstackdemo.domain;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Optional;

/**
 * maintain use data
 * exported 'false' prevents /users endpoint from being visible from localhost:8080/api
 */
//@RepositoryRestResource(exported = false)
@RepositoryRestResource
public interface CustomUserRepository extends CrudRepository<CustomUser, Long> {
    Optional<CustomUser> findById(Long id);

    Optional<CustomUser> findByUsername(String username);

    Optional<CustomUser> findByPassword(String password);
}
