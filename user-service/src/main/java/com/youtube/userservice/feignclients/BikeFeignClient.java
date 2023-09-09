package com.youtube.userservice.feignclients;

import com.youtube.userservice.model.Bike;
import com.youtube.userservice.model.Car;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@FeignClient(name = "bike-service", url = "http://localhost:6063/api/bikes")
public interface BikeFeignClient {
    @PostMapping()
    Bike save(Bike bike);

    @GetMapping("/byuser/{userId}")
    List<Bike> getBikes(@PathVariable("userId") int userId);
}
