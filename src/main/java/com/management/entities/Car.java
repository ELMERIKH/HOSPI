package com.management.entities;

import jakarta.persistence.*;
import org.jetbrains.annotations.NotNull;

import org.springframework.data.jpa.repository.JpaRepository;


@Entity
@Table(name="car")
public class Car {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String make;


    private String model;
    private String Color;

    @NotNull

    private Integer year;

    @NotNull

    private Double price;

    public Car() {
    }

    public Car(String make, String model, String Color, Integer year, Double price) {
        this.make = make;
        this.model = model;
        this.year = year;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
