package com.dev.fullstackdemo;

import com.dev.fullstackdemo.web.AuthenticationController;
import com.dev.fullstackdemo.web.CarController;
import com.dev.fullstackdemo.web.CustomUserController;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.*;

@SpringBootTest
class FullstackdemoApplicationTests {

    @Autowired
    private AuthenticationController authenticationController;
    @Autowired
    private CarController carController;
    @Autowired
    private CustomUserController customUserController;

    @Test
    @DisplayName("Verify rest controllers are loaded")
    void contextLoads() {
        assertThat(authenticationController).isNotNull();
        assertThat(carController).isNotNull();
        assertThat(customUserController).isNotNull();
    }

}
