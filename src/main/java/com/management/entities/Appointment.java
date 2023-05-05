package com.management.entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    private Doctor doctor;
    @ManyToOne
    private Patient patient;
    private LocalDateTime StartTime;
    private LocalDateTime EndTime;
    @Column(name = "doctor_id", insertable = false, updatable = false)
    private Long doctorId;
    @Column(name = "patient_id", insertable = false, updatable = false)
    private Long patientId;
    private boolean accepted;



    private String status ;


    // getters and setters

    // Update the constructor to set the patient ID to the ID of the patient object
    public Appointment() {
        this.patient = new Patient(doctor);
        this.doctor = new Doctor();
        this.patientId = this.patient.getId();
        this.doctorId = this.doctor.getId();
    }

    // Update the setters to set the patient ID to the ID of the patient object
    public void setPatient(Patient patient) {
        this.patient = patient;
        this.patientId = patient.getId();
    }

    // Add getters and setters for the patient ID
    public Long getPatientId() {
        return patientId;
    }

    public void setPatientId(Long patientId) {
        this.patientId = patientId;
    }
    public Long getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(Long doctorId) {
        this.doctorId = doctorId;
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
        this.doctorId = doctor.getId();
    }

    public Patient getPatient() {
        return patient;
    }



    public LocalDateTime getStartTime() {
        return StartTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        StartTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return EndTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        EndTime = endTime;
    }

    public boolean isAccepted() {
        return accepted;
    }

    public void setAccepted(boolean accepted) {
        this.accepted = accepted;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }



}