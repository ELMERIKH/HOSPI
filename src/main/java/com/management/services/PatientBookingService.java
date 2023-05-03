package com.management.services;

import com.management.entities.Appointment;
import com.management.entities.Patient;

import java.util.List;
import java.util.Optional;

public interface PatientBookingService {
    List<Appointment> getAllAppointments();

    Patient getPatientById(long id);

    Optional<Appointment> getAppointmentById(Long id);
    Appointment bookAppointment(Appointment appointment);
    Appointment updateAppointment(Appointment appointment);
    void cancelAppointment(Long id);

    Appointment acceptAppointment(Long appointmentId);

    Appointment rejectAppointment(Long appointmentId);

    List<Appointment> getPendingAppointmentsForDoctor(Long doctorId);

    List<Appointment> getAcceptedAppointmentsForDoctor(Long doctorId);
}