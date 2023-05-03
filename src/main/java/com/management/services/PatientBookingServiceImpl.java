package com.management.services;

import com.management.entities.Appointment;
import com.management.entities.Doctor;
import com.management.entities.Patient;
import com.management.repositories.AppointmentRepository;
import com.management.repositories.DoctorRepository;
import com.management.repositories.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PatientBookingServiceImpl implements PatientBookingService {

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private DoctorRepository doctorRepository;

    @Override
    public List<Appointment> getAllAppointments() {
        return appointmentRepository.findAll();
    }
    @Override
    public Patient getPatientById(long id) {
        return patientRepository.getPatientsById(id);
    }

    @Override
    public Optional<Appointment> getAppointmentById(Long id) {
        return appointmentRepository.findById(id);

    }


    @Override
    public Appointment bookAppointment(Appointment appointmentDTO) {
        Patient patient = patientRepository.findById(appointmentDTO.getPatientId())
                .orElseThrow(() -> new ResourceNotFoundException("Patient not found","",""));
        Doctor doctor = doctorRepository.findById(appointmentDTO.getDoctorId())
                .orElseThrow(() -> new ResourceNotFoundException("Doctor not found","",""));

        Appointment appointment = new Appointment();
        appointment.setPatient(patient);
        appointment.setDoctor(doctor);
        appointment.setStartTime(appointmentDTO.getStartTime());
        appointment.setStatus("PENDING");

        return appointmentRepository.save(appointment);
    }

    @Override
    public Appointment updateAppointment(Appointment appointment) {
        return null;
    }

    @Override
    public void cancelAppointment(Long id) {

    }

    @Override
    public Appointment acceptAppointment(Long appointmentId) {
        Appointment appointment = appointmentRepository.findById(appointmentId)
                .orElseThrow(() -> new ResourceNotFoundException("Appointment not found",",",""));

        appointment.setStatus("ACCEPTED");
        return appointmentRepository.save(appointment);
    }

    @Override
    public Appointment rejectAppointment(Long appointmentId) {
        Appointment appointment = appointmentRepository.findById(appointmentId)
                .orElseThrow(() -> new ResourceNotFoundException("Appointment not found",",",""));

        appointment.setStatus("REJECTED");
        return appointmentRepository.save(appointment);
    }

    @Override
    public List<Appointment> getPendingAppointmentsForDoctor(Long doctorId) {
        Doctor doctor = doctorRepository.findById(doctorId)
                .orElseThrow(() -> new ResourceNotFoundException("Doctor not found","",""));

        return appointmentRepository.findByDoctorAndStatus(doctor, "PENDING");
    }
}
