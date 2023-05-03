package com.management.repositories;


import com.management.entities.Appointment;
import com.management.entities.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

    List<Appointment> findByDoctorId(Long doctorId);
    Optional<Appointment> findById(long Id);

    List<Appointment> findByDoctorAndStatus(Doctor doctor, String pending);
}