package com.management.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.time.Instant;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


@Entity

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {
    @Id
    private String id;
    @NotEmpty(message = "username is Empty")
    @NotBlank(message = "username is Blank")
    @Column(unique=true)
    private String username;
    private String firstname;
    private String lastname;
    private String fullname;
    @NotBlank(message = "password required")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
    @Email(message = "Email is not valid", regexp = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])")
    @NotEmpty(message = "Email is empty")
    @Column(unique=true)
    private String email;
    private Instant createdAt;

    public Long getDoctorId() {

        return   getDoctor().getId();
    }
    public Doctor getDoctor() {
        return doctor;
    }


    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    private Boolean enabled;
    @Column(nullable = true,name = "token_signature")
    private String tokenSignature;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<Role> roles= new ArrayList<>();  ;
    @OneToOne
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;
    @OneToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }













    public void setUserId(String string) {

    }
    public List<Role> getRoles(){
        return roles;
    }


}