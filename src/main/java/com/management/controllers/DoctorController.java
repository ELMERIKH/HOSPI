package com.management.controllers;

import com.management.entities.*;
import com.management.repositories.DoctorRepository;
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
@RequestMapping("/doctors")
public class DoctorController {
    @Autowired
    private DoctorService doctorService;
@Autowired
private DoctorRepository doctorRepository;
    @Autowired
    private PatientBookingServiceImpl patientBookingServiceImpl;

    @Autowired
    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }
    @GetMapping({"/index"})
    public String index(Model model, @RequestParam(name = "page",defaultValue = "0") int page, @RequestParam(name = "size",defaultValue = "10") int size, @RequestParam(name = "keyword",defaultValue = "") String kw) {
        Page<Doctor> pagePatients = this.doctorRepository.findByNameContains(kw, PageRequest.of(page, size));
        model.addAttribute("listDoctors", pagePatients.getContent());
        model.addAttribute("pages", new int[pagePatients.getTotalPages()]);
        model.addAttribute("currentPage", page);
        model.addAttribute("keyword", kw);
        return "Doctors";
    }


    @GetMapping("/{id}/appointments")
    public String showAppointments(@PathVariable("id") Long doctorId, Model model) {
        Doctor doctor = doctorService.getDoctorById(doctorId);
        List<Appointment> appointments = doctor.getAppointments();
        model.addAttribute("doctor", doctor);
        model.addAttribute("appointments", appointments);
        return "appointments";
    }
    @PostMapping("/{id}/appointments/{appointmentId}")
    public String updateAppointment(@PathVariable("id") Long doctorId,
                                    @PathVariable("appointmentId") Long appointmentId,
                                    @RequestParam("status") String status) {
        doctorService.updateAppointmentStatus(doctorId, appointmentId, status);
        return "redirect:/doctors/{id}/appointments";
    }
    @GetMapping("/{id}/patients")
    public String showPatients(@PathVariable("id") Long doctorId, Model model) {
        Doctor doctor = doctorService.getDoctorById(doctorId);
        List<Patient> patients = doctor.getPatients();
        model.addAttribute("doctor", doctor);
        model.addAttribute("patients", patients);
        return "patients";
    }
    @GetMapping("/add")
    public ModelAndView addDoctor() {
        ModelAndView modelAndView = new ModelAndView("add-Doctors");
        modelAndView.addObject("doctor", new Doctor());
        return modelAndView;
    }



    @PostMapping("/add")
    public ModelAndView addDoctorSubmit(@ModelAttribute Doctor doctor) {
        doctorService.createDoctor(doctor);
        return new ModelAndView("redirect:/doctors/list");
    }
    @GetMapping("/appointments")
    public String viewAppointments(Model model) {
        List<Appointment> appointments = patientBookingServiceImpl.getAllAppointments();
        model.addAttribute("appointments", appointments);
        return "appointments";
    }

    @GetMapping("/appointments/{id}")
    public String viewAppointmentDetails(@PathVariable("id") Long id, Model model) {
        Optional<Appointment> appointment = patientBookingServiceImpl.getAppointmentById(id);
        model.addAttribute("appointment", appointment);
        return "appointment-details";
    }

    @PostMapping("/appointments/{id}/accept")
    public String acceptAppointment(@PathVariable("id") Long id) {
        patientBookingServiceImpl.acceptAppointment(id);

        return "redirect:/doctors/appointments";
    }

    @PostMapping("/appointments/{id}/deny")
    public String denyAppointment(@PathVariable("id") Long id) {
        patientBookingServiceImpl.rejectAppointment(id);
        return "redirect:/doctors/appointments";
    }
    @GetMapping("/calendar")
    public String showCalendar(Model model) {


        return "Calendar"; // Return the name of the calendar view template
    }
    @GetMapping("/events")
    @ResponseBody
    public List<Map<String, Object>> getEvents() {
        List<Map<String, Object>> calendarEvents = new ArrayList<>();

        // First event
        Map<String, Object> event1 = new HashMap<>();
        event1.put("title", "Meeting with John");
        event1.put("start", "2023-05-02T10:00:00");
        event1.put("end", "2023-05-02T11:00:00");
        calendarEvents.add(event1);

        // Second event
        Map<String, Object> event2 = new HashMap<>();
        event2.put("title", "Lunch with Susan");
        event2.put("start", "2023-05-02T12:00:00");
        event2.put("end", "2023-05-02T13:00:00");
        calendarEvents.add(event2);

        return calendarEvents;
    }
}