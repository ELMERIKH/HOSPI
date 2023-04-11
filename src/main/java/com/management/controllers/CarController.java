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
        ModelAndView modelAndView = new ModelAndView("Cars-List");
        modelAndView.addObject("cars", cars);
        return modelAndView;
    }
    @GetMapping("/showCars")
    public String showCars(Model model) {
        List<Car> cars = carService.getAllCars();
        model.addAllAttributes(cars);
        return "Cars-List";
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

    @PutMapping("/{id}")
    public ResponseEntity<Car> updateCar(@PathVariable(value = "id") Long id, @RequestBody Car carDetails) {
        Car updatedCar = carService.updateCar(id, carDetails);
        return ResponseEntity.ok(updatedCar);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCar(@PathVariable(value = "id") Long id) {
        carService.deleteCar(id);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/add")
    public String addCar(Model model) {
        model.addAttribute("car", new Car());
        return "addCar";
    }
    @PostMapping("/add")
    public String addCarSubmit(@ModelAttribute Car car) {
        carService.createCar(car);
        return "redirect:/cars";
    }
}
