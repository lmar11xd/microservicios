package com.youtube.userservice.feignclients;

import com.youtube.userservice.model.Bike;
import com.youtube.userservice.model.Car;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@FeignClient(name = "bike-service")
public interface BikeFeignClient {
    @PostMapping("/api/bikes")
    Bike save(Bike bike);

    @GetMapping("/api/bikes/byuser/{userId}")
    List<Bike> getBikes(@PathVariable("userId") int userId);
}
