package com.management.entities;

import jakarta.persistence.*;
import org.jetbrains.annotations.NotNull;

import org.springframework.data.jpa.repository.JpaRepository;

import java.awt.*;


@Entity
@Table(name="car")
public class Car {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String make;

    private String image;
    private String model;
    private String color;

    @NotNull

    private Integer year;

    @NotNull

    private Double price;

    public Car() {
    }

    public Car(String make, String model,String image, String color, Integer year, Double price) {
        this.make = make;
        this.model = model;
        this.year = year;
        this.price = price;
        this.color=color;
        this.image=image;
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
    public void setImage(String image) {
        this.image = image;
    }

    public String getImage() {
        return image;
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
    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
