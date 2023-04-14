package com.management.controllers;

import com.management.entities.Car;
import com.management.services.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;
@RestController
@RequestMapping("/")
public class CarController {

    @Autowired
    private CarService carService;

    @RequestMapping(value = "/list", method = RequestMethod.GET,
            headers = "Accept=application/json")
    public ModelAndView showCars() {
        List<Car> cars = carService.getAllCars();
        ModelAndView modelAndView = new ModelAndView("CarList");
        modelAndView.addObject("cars", cars);
        return modelAndView;
    }


    @GetMapping("/")
    public List<Car> getAllCars() {
        return carService.getAllCars();
    }

    @GetMapping("/car/{id}")
    public ResponseEntity<Car> getCarById(@PathVariable(value = "id") Long id) {

        Optional<Car> car = carService.getCarById(id);

        if (car.isPresent()) {
            return ResponseEntity.ok().body(car.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/make/{make}")
    public List<Car> getCarsByMake(@PathVariable(value = "make") String make) {
        return carService.getCarsByMake(make);
    }

    @PostMapping("/")
    public Car createCar(@RequestBody Car car) {
        return carService.createCar(car);
    }

    @PostMapping("edit/{id}")
    public ModelAndView updateCar(@PathVariable(value = "id") Long id,  Car carDetails) {

       carService.updateCar(id, carDetails);

        return new ModelAndView("redirect:/list");
    }  @GetMapping("/edit/{id}")
    public ModelAndView editCar(@PathVariable(value = "id") Long id) {
        ModelAndView modelAndView = new ModelAndView("Edit");
        modelAndView.addObject("car", carService.getCarById(id));
        return modelAndView;
    }

    @DeleteMapping ("/delete")
    public ResponseEntity<?> deleteCar(@PathVariable(value = "id") Long id) {
        carService.deleteCar(id);
        return ResponseEntity.noContent().build();
    }
    @PostMapping("/delete/{id}")
    public ModelAndView deleteSubmit(@PathVariable(value = "id")Long id) {
        carService.deleteCar(id);
        return new ModelAndView("redirect:/list");
    }
    @GetMapping("/add")
    public ModelAndView addCar() {
        ModelAndView modelAndView = new ModelAndView("add-car");
        modelAndView.addObject("car", new Car());
        return modelAndView;
    }


    @PostMapping("/add")
    public ModelAndView addCarSubmit(@ModelAttribute Car car) {
        carService.createCar(car);
        return new ModelAndView("redirect:/list");
    }
}
