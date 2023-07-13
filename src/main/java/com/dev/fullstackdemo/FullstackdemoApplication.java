package com.dev.fullstackdemo;

import com.dev.fullstackdemo.domain.Car;
import com.dev.fullstackdemo.domain.CarRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FullstackdemoApplication implements CommandLineRunner {
    private static final Logger LOGGER = LoggerFactory.getLogger(FullstackdemoApplication.class);

    @Autowired
    private CarRepository carRepository;

    public static void main(String[] args) {
        SpringApplication.run(FullstackdemoApplication.class, args);
        LOGGER.info("Application started");
    }

    // use CommandLineRunner to implement additional logic before application is started
    @Override
    public void run(String... args) {
        carRepository.save(new Car("Ford", "Mustang", "Red", "222-3321", 1999, 24000));
        carRepository.save(new Car("Nissan", "Murano", "Black", "108-7821", 2016, 34000));

        //fetch all cars
        LOGGER.info("Fetching data");
        for (Car car : carRepository.findAll()) {
            LOGGER.info("Car Brand: " + car.getBrand());
        }
    }
}
