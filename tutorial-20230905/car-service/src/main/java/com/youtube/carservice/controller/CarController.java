package com.youtube.carservice.controller;

import com.youtube.carservice.entity.Car;
import com.youtube.carservice.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/cars")
public class CarController {
    @Autowired
    private CarService carService;

    @GetMapping()
    public ResponseEntity<List<Car>> getAll() {
        List<Car> cars = carService.getAll();
        if(cars.isEmpty())
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(cars);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Car> getById(@PathVariable("id") int id) {
        Car car = carService.getById(id);
        if(car == null)
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(car);
    }

    @PostMapping()
    public ResponseEntity<Car> save(@RequestBody Car car) {
        Car carNew = carService.save(car);
        if(carNew == null)
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(car);
    }

    @GetMapping("/byuser/{userId}")
    public ResponseEntity<List<Car>> getByUserId(@PathVariable("userId") int userId) {
        List<Car> cars = carService.getByUserId(userId);
        /*if(cars.isEmpty())
            return ResponseEntity.noContent().build();*/
        return ResponseEntity.ok(cars);
    }
}
