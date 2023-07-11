package com.dev.fullstackdemo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FullstackdemoApplication {
	private static final Logger LOGGER = LoggerFactory.getLogger(FullstackdemoApplication.class);
    public static void main(String[] args) {
        SpringApplication.run(FullstackdemoApplication.class, args);
        LOGGER.info("Application started");
    }

}
