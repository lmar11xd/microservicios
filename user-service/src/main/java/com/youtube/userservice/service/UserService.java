package com.youtube.userservice.service;

import com.youtube.userservice.entity.User;
import com.youtube.userservice.feignclients.BikeFeignClient;
import com.youtube.userservice.feignclients.CarFeignClient;
import com.youtube.userservice.model.Bike;
import com.youtube.userservice.model.Car;
import com.youtube.userservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private CarFeignClient carFeignClient;

    @Autowired
    private BikeFeignClient bikeFeignClient;

    public List<User> getAll() {
        return userRepository.findAll();
    }

    public User getById(int id) {
        return userRepository.findById(id).orElse(null);
    }

    public User save(User user) {
        User userNew = userRepository.save(user);
        return userNew;
    }

    public List<Car> getCars(int userId) {
        List<Car> cars = restTemplate.getForObject("http://car-service/api/cars/byuser/" + userId, List.class);
        return cars;
    }

    public List<Bike> getBikes(int userId) {
        List<Bike> bikes = restTemplate.getForObject("http://bike-service/api/bikes/byuser/" + userId, List.class);
        return bikes;
    }

    public Car saveCar(int userId, Car car) {
        car.setUserId(userId);
        Car carNew = carFeignClient.save(car);
        return carNew;
    }

    public Bike saveBike(int userId, Bike bike) {
        bike.setUserId(userId);
        Bike bikeNew = bikeFeignClient.save(bike);
        return bikeNew;
    }

    public Map<String, Object> getUserVehicles(int userId) {
        Map<String, Object> result = new HashMap<>();
        User user = userRepository.findById(userId).orElse(null);
        if(user == null) {
            result.put("Mensaje", "User not found");
            return result;
        }

        result.put("User", user);

        List<Car> cars = carFeignClient.getCars(userId);
        if(cars.isEmpty()) result.put("Cars", "This user doesn't have cars");
        else result.put("Cars", cars);

        List<Bike> bikes = bikeFeignClient.getBikes(userId);
        if(bikes.isEmpty()) result.put("Bikes", "This user doesn't have bikes");
        else result.put("Bikes", bikes);

        return result;
    }
}
