package com.management.controllers;


import com.management.entities.Appointment;
import com.management.entities.Doctor;
import com.management.entities.Patient;
import com.management.repositories.PatientRepository;
import com.management.services.DoctorService;
import com.management.services.PatientBookingServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Controller
public class PatientController {
    @Autowired
    private PatientRepository patientRepository;
    @Autowired
    private PatientBookingServiceImpl patientBookingServiceImpl;
    @Autowired
    private DoctorService doctorService;

    public PatientController() {
    }
    @GetMapping({"/home"})
    public String index() {

        return "Home";
    }
    @GetMapping({"/index"})
    public String index(Model model, @RequestParam(name = "page",defaultValue = "0") int page, @RequestParam(name = "size",defaultValue = "5") int size, @RequestParam(name = "keyword",defaultValue = "") String kw) {
        Page<Patient> pagePatients = this.patientRepository.findByNomContains(kw, PageRequest.of(page, size));
        model.addAttribute("listPatients", pagePatients.getContent());
        model.addAttribute("pages", new int[pagePatients.getTotalPages()]);
        model.addAttribute("currentPage", page);
        model.addAttribute("keyword", kw);
        return "patients";
    }

    @GetMapping({"/deletePatient"})
    public String deletePatient(@RequestParam(name = "id") Long id, String keyword, int page) {
        this.patientRepository.deleteById(id);
        return "redirect:/index?page=" + page + "&keyword=" + keyword;
    }

    @GetMapping({"/formPatient"})
    public String formPatient(Model model) {
        model.addAttribute("patient", new Patient());
        return "formPatient";
    }

    @PostMapping({"/savePatient"})
    public String savePatient(@Valid Patient patient, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "formPatient";
        } else {
            this.patientRepository.save(patient);
            return "formPatient";
        }
    }

    @GetMapping({"/editPatient"})
    public String editPatient(@RequestParam(name = "id") Long id, Model model) {
        Patient patient = (Patient)this.patientRepository.findById(id).get();
        model.addAttribute("patient", patient);
        return "editPatient";
    }
    @GetMapping("/search")
    public String searchDoctors(Model model,
                                @RequestParam("city") String city,
                                @RequestParam("specialty") String specialty) {
        List<Doctor> doctors = doctorService.findDoctorsByCityAndSpecialty(city,specialty);

        model.addAttribute("doctors", doctors);
        return "doctor-results";
    }
    @GetMapping("/request-appointment/{doctorId}")
    public String showAppointmentForm(@PathVariable("doctorId") Long doctorId, @RequestParam(value = "patientId", required = false) Long patientId, Model model)  {
        Doctor doctor = doctorService.getDoctorById(doctorId);
        Appointment appointment = new Appointment();
        appointment.setDoctor(doctor);

        // Check if patient is authenticated


        model.addAttribute("appointment", appointment);



        return "appointment-request-form";
    }
    @PostMapping("/request-appointment/{doctorId}")
    public String submitAppointmentRequestForm(@RequestParam("nom") String name,
                                               @RequestParam("email") String email,
                                               @RequestParam("dateNaissance") @DateTimeFormat(pattern = "yyyy-MM-dd") Date dateNaissance,
                                               @RequestParam("phone") long phone,
                                               @ModelAttribute("appointment") Appointment appointment,
                                               @RequestParam("date") @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss") LocalDateTime dateTime) {

        // Convert LocalDateTime to Date object

        Patient patient = new Patient();
        patient.setNom(name);
        patient.setEmail(email);
        patient.setPhone(phone);
        patient.setDateNaissance(dateNaissance);
        // set other properties as needed
        patientRepository.save(patient);

        appointment.setPatient(patient);
        appointment.setStartTime(dateTime);
        patientBookingServiceImpl.bookAppointment(appointment);

        return "redirect:/doctors/appointments";
    }
    @GetMapping("/appointments/new")
    public String showNewAppointmentForm(Model model) {
        Appointment appointment = new Appointment();
        model.addAttribute("appointment", appointment);
        return "new-appointment-form";
    }

    @PostMapping("/appointments/new")
    public String submitNewAppointmentForm(@ModelAttribute("appointmentRequest") Appointment appointment, BindingResult result) {
        if (result.hasErrors()) {
            return "new-appointment-form";
        }
        patientBookingServiceImpl.bookAppointment(appointment);
        return "redirect:/patients/appointments";
    }
}
