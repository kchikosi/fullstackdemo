package com.dev.fullstackdemo;

import com.dev.fullstackdemo.domain.*;
import com.dev.fullstackdemo.service.UserServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;

@SpringBootApplication
public class FullstackdemoApplication implements CommandLineRunner {
    private static final Logger LOGGER = LoggerFactory.getLogger(FullstackdemoApplication.class);

    @Autowired
    private OwnerRepository ownerRepository;
    @Autowired
    private CarRepository carRepository;
    //    @Autowired
//    private UserRepository userRepository;
    @Autowired
    private UserServiceImpl userService;

    public static void main(String[] args) {
        SpringApplication.run(FullstackdemoApplication.class, args);
        LOGGER.info("Application started");
    }

    // use CommandLineRunner to implement additional logic before application is started
    @Override
    public void run(String... args) {
        Owner ownerOne = new Owner("Kotsanai", "Chikosi");
        Owner ownerTwo = new Owner("Sam", "Douche");
        ownerRepository.saveAll(Arrays.asList(ownerOne, ownerTwo));
        Car carA = new Car("Ford", "Mustang", "Red", "222-3321", 1999, 24000, ownerTwo);
        Car carB = new Car("Nissan", "Murano", "Black", "108-7821", 2016, 34000, ownerOne);
        Car carC = new Car("Nissan", "Ariya", "White", "221-4511", 2023, 54000, ownerOne);
        carRepository.saveAll(Arrays.asList(carA, carB, carC));

        //TODO: password encryption
        CustomUser customUserOne = new CustomUser("admin", "password", Arrays.asList("ROLE_ADMIN", "ROLE_USER"));
        CustomUser customUserTwo = new CustomUser("test", "password", Arrays.asList("ROLE_USER"));
        Arrays.asList(customUserOne, customUserTwo).forEach(customUser -> userService.saveUser(customUser));


        //fetch all cars
        LOGGER.info("Fetching data");
        carRepository.findAll().forEach(car -> LOGGER.info("Vehicle -> " + car.getBrand() + ", " + car.getModel()));
    }
}
