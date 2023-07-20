package com.dev.fullstackdemo.web;

import com.dev.fullstackdemo.domain.Car;
import com.dev.fullstackdemo.domain.CarRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1/vehicles")
public class CarController {
    @Autowired
    private CarRepository carRepository;

    public CarController() {
    }

    public CarController(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    @GetMapping(value = "")
    public ResponseEntity<List<Car>> all(){
        return new ResponseEntity(this.carRepository.findAll(), null, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity get(@PathVariable("id") Long id) throws Exception {
        final Optional<Car> byId = Optional.ofNullable(carRepository.findById(id).orElseThrow(Exception::new));
        return new ResponseEntity(byId, null, HttpStatus.OK);
    }

    //TODO: create a vehicle form
    @PostMapping(value = "")
    public ResponseEntity save(@RequestBody Car car, HttpServletRequest request) {
        return new ResponseEntity(carRepository.save(car), null, HttpStatus.CREATED);
    }
}
