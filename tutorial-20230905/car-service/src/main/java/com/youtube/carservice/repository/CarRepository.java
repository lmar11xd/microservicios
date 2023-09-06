package com.youtube.carservice.repository;

import com.youtube.carservice.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<Car, Integer> {

    public List<Car> getByUserId(int userId);
}
