package com.management.services;


import com.management.entities.Car;
import com.management.repositories.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.management.services.CarService;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class CarService extends IOException {

    @Autowired
    private CarRepository carRepository;

    public List<Car> getAllCars() {
        return carRepository.findAll();
    }

    public Optional<Car> getCarById(Long id) {
        return carRepository.findById(id);
    }

    public List<Car> getCarsByMake(String make) {
        return carRepository.findByMake(make);
    }



    public Car createCar(Car car) {
        return carRepository.save(car);
    }

    public Car updateCar(Long id, Car carDetails) {
        Optional<Car> optionalCar = carRepository.findById(id);

        if (optionalCar.isPresent()) {
            Car car = optionalCar.get();
            car.setMake(carDetails.getMake());
            car.setModel(carDetails.getModel());
            car.setYear(carDetails.getYear());
            car.setPrice(carDetails.getPrice());
            car.setColor(carDetails.getColor());
            return carRepository.save(car);
        } else {
            throw   new ResourceNotFoundException("Car", "id", id);
        }
    }

    public void deleteCar(Long id) {
        Optional<Car> optionalCar = carRepository.findById(id);

        if (optionalCar.isPresent()) {
            carRepository.deleteById(id);
        } else {
            throw  new ResourceNotFoundException("Car", "id", id);
        }
    }
}