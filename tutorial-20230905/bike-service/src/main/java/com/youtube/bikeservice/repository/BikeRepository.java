package com.youtube.bikeservice.repository;

import com.youtube.bikeservice.entity.Bike;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BikeRepository extends JpaRepository<Bike, Integer> {

    public List<Bike> getByUserId(int userId);
}
