package com.youtube.bikeservice.controller;

import com.youtube.bikeservice.entity.Bike;
import com.youtube.bikeservice.service.BikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/bikes")
public class BikeController {
    @Autowired
    private BikeService bikeService;

    @GetMapping()
    public ResponseEntity<List<Bike>> getAll() {
        List<Bike> bikes = bikeService.getAll();
        if(bikes.isEmpty())
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(bikes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Bike> getById(@PathVariable("id") int id) {
        Bike bike = bikeService.getById(id);
        if(bike == null)
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(bike);
    }

    @PostMapping()
    public ResponseEntity<Bike> save(@RequestBody Bike bike) {
        Bike bikeNew = bikeService.save(bike);
        if(bikeNew == null)
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(bike);
    }

    @GetMapping("/byuser/{userId}")
    public ResponseEntity<List<Bike>> getByUserId(@PathVariable("userId") int userId) {
        List<Bike> bikes = bikeService.getByUserId(userId);
        /*if(bikes.isEmpty())
            return ResponseEntity.noContent().build();*/
        return ResponseEntity.ok(bikes);
    }
}
