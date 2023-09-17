package com.youtube.userservice.controller;

import com.youtube.userservice.entity.User;
import com.youtube.userservice.model.Bike;
import com.youtube.userservice.model.Car;
import com.youtube.userservice.service.UserService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/users")
public class UserController {
    @Autowired
    private UserService userService; 

    @GetMapping
    public ResponseEntity<List<User>> getAll() {
        List<User> users = userService.getAll();
        if(users.isEmpty())
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getById(@PathVariable("id") int id) {
        User user = userService.getById(id);
        if(user == null)
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(user);
    }

    @PostMapping()
    public ResponseEntity<User> save(@RequestBody User user) {
        User userNew = userService.save(user);
        if(user == null)
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(user);
    }

    @CircuitBreaker(name = "carsCB", fallbackMethod = "fallBackGetCars")
    @GetMapping("/cars/{userId}")
    public ResponseEntity<List<Car>> getCars(@PathVariable("userId") int userId) {
        User user = userService.getById(userId);
        if(user == null)
            return ResponseEntity.notFound().build();

        List<Car> cars = userService.getCars(userId);
        return ResponseEntity.ok(cars);
    }

    @CircuitBreaker(name = "carsCB", fallbackMethod = "fallBackSaveCar")
    @PostMapping("/savecar/{userId}")
    public ResponseEntity<Car> saveCar(@PathVariable("userId") int userId, @RequestBody Car car) {
        if(userService.getById(userId) == null)
            return ResponseEntity.notFound().build();

        Car carNew = userService.saveCar(userId, car);
        return ResponseEntity.ok(carNew);
    }

    @CircuitBreaker(name = "bikesCB", fallbackMethod = "fallBackGetBikes")
    @GetMapping("/bikes/{userId}")
    public ResponseEntity<List<Bike>> getBikes(@PathVariable("userId") int userId) {
        User user = userService.getById(userId);
        if(user == null)
            return ResponseEntity.notFound().build();

        List<Bike> bikes = userService.getBikes(userId);
        return ResponseEntity.ok(bikes);
    }

    @CircuitBreaker(name = "bikesCB", fallbackMethod = "fallBackSaveBike")
    @PostMapping("/savebike/{userId}")
    public ResponseEntity<Bike> saveBike(@PathVariable("userId") int userId, @RequestBody Bike bike) {
        if(userService.getById(userId) == null)
            return ResponseEntity.notFound().build();

        Bike bikeNew = userService.saveBike(userId, bike);
        return ResponseEntity.ok(bikeNew);
    }

    @CircuitBreaker(name = "vehiclesCB", fallbackMethod = "fallBackGetVehicles")
    @GetMapping("/vehicles/{userId}")
    public ResponseEntity<Map<String, Object>> getVehicles(@PathVariable("userId") int userId) {
        Map<String, Object> result = userService.getUserVehicles(userId);
        return ResponseEntity.ok(result);
    }

    public ResponseEntity<List<Car>> fallBackGetCars(@PathVariable("userId") int userId, RuntimeException e) {
        return new ResponseEntity("El usuario " + userId + " tiene los carros en el taller", HttpStatus.OK);
    }

    public ResponseEntity<Car> fallBackSaveCar(@PathVariable("userId") int userId, @RequestBody Car car, RuntimeException e) {
        return new ResponseEntity("El usuario " + userId + " no tiene dinero para carros", HttpStatus.OK);
    }

    public ResponseEntity<List<Bike>> fallBackGetBikes(@PathVariable("userId") int userId, RuntimeException e) {
        return new ResponseEntity("El usuario " + userId + " tiene las motos en el taller", HttpStatus.OK);
    }

    public ResponseEntity<Bike> fallBackSaveBike(@PathVariable("userId") int userId, @RequestBody Bike bike, RuntimeException e) {
        return new ResponseEntity("El usuario " + userId + " no tiene dinero para motos", HttpStatus.OK);
    }

    public ResponseEntity<Bike> fallBackGetVehicles(@PathVariable("userId") int userId, RuntimeException e) {
        return new ResponseEntity("El usuario " + userId + " tiene los vehiculos en el taller", HttpStatus.OK);
    }
}
