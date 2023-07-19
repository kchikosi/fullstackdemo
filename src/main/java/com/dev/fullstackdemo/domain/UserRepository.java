package com.dev.fullstackdemo.domain;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

/**
 * maintain use data
 */
public interface UserRepository extends CrudRepository<User, Long> {
    Optional<User> findByUsername(String username);
}
