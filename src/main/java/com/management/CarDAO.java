package com.management;

import com.management.entities.Car;

import java.util.List;


    public interface CarDAO {
        public List<Car> getAllCars();
        public Car getCarById(int id);
        public void addCar(Car car);
        public void updateCar(Car car);
        public void deleteCar(int id);
    }

