package com.management.entities;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String image;
    @OneToMany(mappedBy = "doctorId", cascade = CascadeType.ALL)
    private List<Appointment> appointments ;
    @OneToMany(mappedBy = "doctor", cascade = CascadeType.ALL)
    private List<Patient> Patients ;

    private String specialty;

    public  Doctor(){};

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getSpecialty() {
        return specialty;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Doctor(Long id, String name, String email, List<Appointment> appointments, List<Patient> patients, String specialty, String city, String image) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.appointments = appointments;
        this.Patients = patients;
        this.specialty = specialty;
        this.city = city;
        this.image=image;
    }

    private String city;
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Appointment> getAppointments() {
        return appointments;
    }

    public void setAppointments(List<Appointment> appointments) {
        this.appointments = appointments;
    }
    public List<Patient> getPatients() {
        return Patients;
    }

    public void setPatients(List<Patient> Patients) {
        this.Patients = Patients;
    }




}