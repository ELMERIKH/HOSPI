package com.management.repositories;

import com.management.entities.Doctor;
import com.management.entities.Patient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long> {
    List<Doctor> findBySpecialty(String specialty);
    Doctor findByName(String name);
    List <Doctor> findDoctorsByCityAndSpecialty(String city, String specialty);
    List<Doctor> findByCity(String city);
    Doctor findById(long id);

    Page<Doctor> findByNameContains(String kw, Pageable pageable);
}