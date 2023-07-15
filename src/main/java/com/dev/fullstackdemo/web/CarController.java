package com.dev.fullstackdemo.web;

import com.dev.fullstackdemo.domain.Car;
import com.dev.fullstackdemo.domain.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CarController {
    @Autowired
    private CarRepository carRepository;
    @RequestMapping(value = "/cars", method = RequestMethod.GET)
    public Iterable<Car> getCars(){
        return carRepository.findAll();
    }
}
