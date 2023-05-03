package com.management.repositories;

import com.management.entities.Patient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;



    public interface PatientRepository extends JpaRepository<Patient, Long> {
        Page<Patient> findByNomContains(String kw, Pageable pageable);
        Patient getPatientsById(long patientId);
    }


