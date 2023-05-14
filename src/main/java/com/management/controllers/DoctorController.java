package com.management.controllers;

import com.management.entities.*;
import com.management.repositories.DoctorRepository;
import com.management.repositories.PatientRepository;
import com.management.services.DoctorService;
import com.management.services.PatientBookingService;
import com.management.services.PatientBookingServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDateTime;
import java.util.*;

@Controller

public class DoctorController {
    @Autowired
    private DoctorService doctorService;
@Autowired
private DoctorRepository doctorRepository;
    @Autowired
    private PatientBookingServiceImpl patientBookingServiceImpl;
@Autowired
private PatientRepository patientRepository;
    @Autowired
    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }
    @GetMapping({"/doctors/index"})
    public String index(Model model, @RequestParam(name = "page",defaultValue = "0") int page, @RequestParam(name = "size",defaultValue = "10") int size, @RequestParam(name = "keyword",defaultValue = "") String kw) {
        Page<Doctor> pagePatients = this.doctorRepository.findByNameContains(kw, PageRequest.of(page, size));
        model.addAttribute("listDoctors", pagePatients.getContent());
        model.addAttribute("pages", new int[pagePatients.getTotalPages()]);
        model.addAttribute("currentPage", page);
        model.addAttribute("keyword", kw);
        return "Doctors";
    }


    @GetMapping("/doctors/{id}/appointments")
    public String showAppointments(@PathVariable("id") Long doctorId, Model model) {
        Doctor doctor = doctorService.getDoctorById(doctorId);
        List<Appointment> appointments = patientBookingServiceImpl.getPendingAppointmentsForDoctor(doctorId);
        model.addAttribute("doctor", doctor);
        model.addAttribute("appointments", appointments);
        return "appointments";
    }
    @PostMapping("/doctors/{id}/appointments/{appointmentId}")
    public String updateAppointment(@PathVariable("id") Long doctorId,
                                    @PathVariable("appointmentId") Long appointmentId,
                                    @RequestParam("status") String status) {
        doctorService.updateAppointmentStatus(doctorId, appointmentId, status);
        return "redirect:/doctors/{id}/appointments";
    }
    @GetMapping("/doctors/{id}/patients")
    public String showPatients(@PathVariable("id") Long doctorId,Model model, @RequestParam(defaultValue = "0") int page,@RequestParam(required = false) String keyword) {
        Doctor doctor = doctorService.getDoctorById(doctorId);
        List<Patient> patients ;
        Pageable pageable = PageRequest.of(page, 8); // page size of 8

        Page<Patient> pagePatients ;
        if (keyword != null && !keyword.isEmpty()) {
            pagePatients = patientRepository.findByNomContainsAndDoctor(keyword, doctor, pageable);
            patients = pagePatients.getContent();
        }else {
            pagePatients = doctorService.getPatientsByDoctorId(doctorId, pageable);
            patients = pagePatients.getContent();
        }
        model.addAttribute("doctor", doctor);
        model.addAttribute("patients", patients);
        model.addAttribute("Page", pagePatients);

        model.addAttribute("keyword", keyword);

        return "patients";
    }
    @GetMapping("/doctors/add")
    public ModelAndView addDoctor() {
        ModelAndView modelAndView = new ModelAndView("add-Doctors");
        modelAndView.addObject("doctor", new Doctor());
        return modelAndView;
    }



    @PostMapping("/doctors/add")
    public ModelAndView addDoctorSubmit(@ModelAttribute Doctor doctor) {
        doctorService.createDoctor(doctor);
        return new ModelAndView("redirect:/doctors/list");
    }
    @GetMapping("/doctors/appointments")
    public String viewAppointments(Model model) {
        List<Appointment> appointments = patientBookingServiceImpl.getAllAppointments();
        model.addAttribute("appointments", appointments);
        return "appointments";
    }

    @GetMapping("/doctors/appointments/{id}")
    public String viewAppointmentDetails(@PathVariable("id") Long id, Model model) {
        Optional<Appointment> appointment = patientBookingServiceImpl.getAppointmentById(id);
        model.addAttribute("appointment", appointment);
        return "appointment-details";
    }

    @PostMapping("/doctors/{Did}/appointments/{id}/accept")
    public String acceptAppointment(@PathVariable("Did") Long doctorId, @PathVariable("id") Long id) {
        patientBookingServiceImpl.acceptAppointment(id);
        Appointment appointment = patientBookingServiceImpl.getAppointmentById(id).get();
        Long patientId = appointment.getPatientId();
        doctorService.addPatientToDoctor(doctorId, patientId);
        return "redirect:/doctors/{Did}/appointments";
    }
    @PostMapping("/doctors/{Did}/appointments/{id}/deny")
    public String denyAppointment(@PathVariable("Did") Long doctorId,@PathVariable("id") Long id) {
        patientBookingServiceImpl.rejectAppointment(id);
        return "redirect:/doctors/{Did}/appointments";
    }
    @GetMapping("/doctors/calendar/{id}")
    public String showCalendar(Model model,@PathVariable("id") Long doctorId) {

        model.addAttribute("id",doctorId);
        return "Calendar"; // Return the name of the calendar view template
    }
    @GetMapping("/doctors/events/{id}")
    @ResponseBody
    public List<Map<String, Object>> getEvents(@PathVariable("id") Long doctorId) {
        List<Appointment> appointments = patientBookingServiceImpl.getAcceptedAppointmentsForDoctor(doctorId);
        List<Map<String, Object>> calendarEvents = new ArrayList<>();

        for (Appointment appointment : appointments) {
            Map<String, Object> event = new HashMap<>();
            event.put("title", appointment.getPatient().getNom());
            event.put("start", appointment.getStartTime().toString());
            calendarEvents.add(event);
        }

        return calendarEvents;
    }

}