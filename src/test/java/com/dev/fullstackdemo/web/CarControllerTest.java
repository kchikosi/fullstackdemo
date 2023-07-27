package com.dev.fullstackdemo.web;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@AutoConfigureMockMvc
class CarControllerTest {

    @Autowired
    private CarController carController;

    @Test
    @DisplayName("Context loads")
    public void contextLoads() {
        assertThat(carController).isNotNull();
    }
}