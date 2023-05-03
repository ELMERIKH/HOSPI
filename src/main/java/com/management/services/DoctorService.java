package com.management.services;

import com.management.entities.Appointment;
import com.management.entities.Doctor;
import com.management.entities.Patient;
import com.management.repositories.AppointmentRepository;
import com.management.repositories.DoctorRepository;
import com.management.repositories.PatientRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service

public class DoctorService {
    @Autowired
    private DoctorRepository doctorRepository;
@Autowired
private PatientRepository patientRepository;
    public List<Doctor> findDoctorsByCityAndSpecialty(String city,String specialty){
        return doctorRepository.findDoctorsByCityAndSpecialty(city,specialty);
    }
    public List<Doctor> getAllDoctors() {
        return doctorRepository.findAll();
    }
    public Doctor getDoctorById(Long id) {
        return doctorRepository.findById(id).orElse(null);
    }
    public Doctor createDoctor(Doctor doctor) {
        return doctorRepository.save(doctor);
    }
    public Doctor updateDoctor(Long id, Doctor doctor) {
        Doctor existingDoctor = doctorRepository.findById(id).orElse(null);
        if(existingDoctor != null) {
            existingDoctor.setName(doctor.getName());
            return doctorRepository.save(existingDoctor);
        }
        return null;
    }
    public boolean deleteDoctor(Long id) {
        Doctor existingDoctor = doctorRepository.findById(id).orElse(null);
        if(existingDoctor != null) {
            doctorRepository.delete(existingDoctor);
            return true;
        }
        return false;
    }

    @Autowired
    private AppointmentRepository appointmentRepository;
    public void updateAppointmentStatus(Long doctorId, Long appointmentId, String status) {
        Doctor doctor = doctorRepository.findById(doctorId).orElseThrow(() -> new ResourceNotFoundException("Doctor not found with id " + doctorId,"",""));
        Appointment appointment = appointmentRepository.findById(appointmentId).orElseThrow(() -> new ResourceNotFoundException("Appointment not found with id " + appointmentId,"",""));

        if (!doctor.getAppointments().contains(appointment)) {
            throw new ResourceNotFoundException("Appointment not found for doctor with id " + doctorId,"id"+ appointmentId,status);
        }

        appointment.setStatus(status);
        appointmentRepository.save(appointment);
    }
    public void addPatientToDoctor(Long doctorId, Long patientId) {
        // Retrieve the doctor object from the database
        Doctor doctor = doctorRepository.findById(doctorId).orElse(null);

        // Retrieve the patient object from the database
        Patient patient = patientRepository.findById(patientId).orElse(null);
        patient.setDoctor(doctor);
        // Add the patient to the doctor's list of patients
        if (doctor != null && patient != null) {
            List<Patient> p=doctor.getPatients();
            p.add(patient);
           doctor.setPatients(p);
            doctorRepository.save(doctor);
        }
    }

    public void acceptAppointment(Long appointmentId) {
        Optional<Appointment> appointment = appointmentRepository.findById(appointmentId);
        if (appointment.isPresent()) {
            appointment.get().setAccepted(true);
            appointmentRepository.save(appointment.get());
        } else {
            throw new RuntimeException("Appointment not found");
        }
    }


    public void denyAppointment(Long appointmentId) {
        Optional<Appointment> appointment = appointmentRepository.findById(appointmentId);
        if (appointment.isPresent()) {
            appointment.get().setAccepted(false);
            appointmentRepository.save(appointment.get());
        } else {
            throw new RuntimeException("Appointment not found");
        }
    }

}
