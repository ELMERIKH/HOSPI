package com.management.repositories;

import com.management.entities.Doctor;
import com.management.entities.Patient;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
    public interface PatientRepository extends JpaRepository<Patient, Long> {
        Page<Patient> findByNomContains(String kw, Pageable pageable);
    @NotNull Page<Patient> findAll(@NotNull Pageable pageable);
    Page<Patient> findByNomContainsAndDoctor(String keyword, Doctor doctor, Pageable pageable);
    Patient getPatientsById(long patientId);

    Page<Patient> getPatientsByDoctorId(Long doctorId, Pageable pageable);
}


