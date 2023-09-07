package com.dev.fullstackdemo.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.security.test.context.support.WithMockUser;

import static org.assertj.core.api.Assertions.*;

@DataJpaTest
class OwnerRepositoryTest {
    @Autowired
    private OwnerRepository ownerRepository;

    //TODO: Complete tests.
    @Test
    @DisplayName("Add new owner")
    @WithMockUser(username = "admin", password = "pw", roles = {"ADMIN"})
    public void saveOwner() {
        ownerRepository.save(new Owner("Don", "King"));
        assertThat(ownerRepository.findByFirstName("Don").isPresent()).isTrue();
    }

    @Test
    @DisplayName("Add and delete an owner")
    public void deleteOwners() {
        ownerRepository.save(new Owner("Tim", "Duncan"));
        ownerRepository.deleteAll();
        assertThat(ownerRepository.count()).isEqualTo(0);
    }
}