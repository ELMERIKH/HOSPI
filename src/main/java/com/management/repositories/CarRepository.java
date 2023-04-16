package com.management.repositories;

import com.management.entities.Car;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Range;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface CarRepository extends JpaRepository<Car, Long> {
        List<Car> findByMake(String make);
    @NotNull Page<Car> findAll(@NotNull Pageable pageable);

    List<Car> findByMakeContainingIgnoreCaseOrModelContainingIgnoreCaseOrColorContainingIgnoreCase(String make, String model, String color);

    Page<Car> findByMakeContainingIgnoreCaseOrModelContainingIgnoreCaseOrColorContainingIgnoreCase(String make, String model, String color, Pageable pageable);
}


