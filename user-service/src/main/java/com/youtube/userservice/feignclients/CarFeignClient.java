package com.youtube.userservice.feignclients;

import com.youtube.userservice.model.Car;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "car-service")
public interface CarFeignClient {
    @PostMapping("/api/cars")
    Car save(@RequestBody Car car);

    @GetMapping("/api/cars/byuser/{userId}")
    List<Car> getCars(@PathVariable("userId") int userId);
}
