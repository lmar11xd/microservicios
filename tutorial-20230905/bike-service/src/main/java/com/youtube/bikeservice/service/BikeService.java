package com.youtube.bikeservice.service;

import com.youtube.bikeservice.entity.Bike;
import com.youtube.bikeservice.repository.BikeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BikeService {
    @Autowired
    private BikeRepository carRepository;

    public List<Bike> getAll() {
        return carRepository.findAll();
    }

    public Bike getById(int id) {
        return carRepository.findById(id).orElse(null);
    }

    public Bike save(Bike bike) {
        Bike bikeNew = carRepository.save(bike);
        return bikeNew;
    }

    public List<Bike> getByUserId(int userId) {
        return carRepository.getByUserId(userId);
    }
}
