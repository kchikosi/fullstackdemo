package com.dev.fullstackdemo.web;

import com.dev.fullstackdemo.domain.Car;
import com.dev.fullstackdemo.domain.CarRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("")
    public ResponseEntity<List<Car>> all() {
        return new ResponseEntity(this.carRepository.findAll(), null, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    @SuppressWarnings("rawtypes")
    public ResponseEntity get(@PathVariable("id") Long id) throws Exception {
        Car vehicle = carRepository.findById(id).orElseThrow(() -> new VehicleNotFoundException(id));
        return new ResponseEntity(vehicle, null, HttpStatus.OK);
    }

    @PostMapping("")
    @SuppressWarnings("rawtypes")
    public ResponseEntity save(@RequestBody Car car, HttpServletRequest request) {
        return new ResponseEntity(carRepository.save(car), null, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity update(@PathVariable("id") long id, @RequestBody Car car) {
        Car vehicle = carRepository.findById(id).orElseThrow(() -> new VehicleNotFoundException(id));
        return new ResponseEntity<>(carRepository.save(vehicle), null, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @SuppressWarnings("rawtypes")
    public ResponseEntity delete(@PathVariable("id") Long id) {
        Car vehicle = carRepository.findById(id).orElseThrow(() -> new VehicleNotFoundException(id));
        carRepository.delete(vehicle);
        return new ResponseEntity(HttpStatus.OK);
    }
}
