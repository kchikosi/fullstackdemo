package com.dev.fullstackdemo.web;

import com.dev.fullstackdemo.service.UserServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class CarControllerTest {

    @Autowired
    private CarController carController;

    @Autowired
    private UserServiceImpl userService;

    @Test
    @DisplayName("Context loads")
    public void contextLoads() {
        assertThat(carController).isNotNull();
    }
}