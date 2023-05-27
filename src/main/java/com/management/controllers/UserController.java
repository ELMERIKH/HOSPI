package com.management.controllers;

import com.management.entities.Doctor;
import com.management.entities.Patient;
import com.management.entities.User;
import com.management.repositories.DoctorRepository;
import com.management.repositories.PatientRepository;
import com.management.repositories.UserRepository;
import com.management.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;


@Controller
    public class UserController {
        @Autowired
        private UserserviceImpl Userservice;
        @Autowired
    DoctorService doctorService;
        @Autowired
    PatientBookingServiceImpl patientBookingService;
       private final
    UserDetailServicesImpl userDetailServices;
    public UserController(UserDetailServicesImpl userDetailsService) {
        this.userDetailServices = userDetailsService;
    }
    @Autowired
    UserRepository userRepository;
    @GetMapping("/register")
    public ModelAndView showRegistrationForm() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("registrationForm");
        modelAndView.addObject("patient", new Patient());

        return modelAndView;
    }
        @PostMapping("/register")
        public ModelAndView register(@ModelAttribute Patient patient,@RequestParam String username,@RequestParam String email, @RequestParam String password, @RequestParam String confirmedPassword) {

         Userservice.saveUser(username,email, password,patient);




            return new ModelAndView("redirect:/login");
        }
    @GetMapping("/register/doctors")
    public ModelAndView showRegistrationFormD() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("RegisterDoc");
        modelAndView.addObject("doctor", new Doctor());

        return modelAndView;
    }
    @PostMapping("/register/doctors")
    public ModelAndView registerDoctor(@ModelAttribute Doctor doctor,@RequestParam String username,@RequestParam String email, @RequestParam String password, @RequestParam String confirmedPassword) {

        Userservice.saveDocter(username,email, password,doctor);




        return new ModelAndView("redirect:/login");
    }
    //@GetMapping("/getid")
    public ResponseEntity<String> getCurrentUserId(Authentication authentication) {
        if (authentication == null || !authentication.isAuthenticated()) {
            // User is not authenticated
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        String username = authentication.getName();
        User user = userRepository.findByUsername(username);

        if (user == null) {
            // User not found
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        String userId = user.getId();
        return ResponseEntity.ok(userId);
    }
    public User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Object principal = authentication.getPrincipal();

        if (principal instanceof UserDetails) {
            UserDetails userDetails = (UserDetails) principal;
            String username = userDetails.getUsername();

            // Assuming you have a service or repository to fetch the user entity based on the username
            return userRepository.findByUsername(username);
        }

        return null; // Return null if the current user is not found or not authenticated
    }
    @GetMapping("/profile")
    public String Profile(Model model) {
        User currentUser = getCurrentUser();

        if (currentUser!=null &&currentUser.getDoctor()!=null&& currentUser.getDoctorId()!=null ) {
            Long doctorId = currentUser.getDoctorId();
           Doctor doctor= doctorService.getDoctorById(doctorId);

            model.addAttribute("user",currentUser );
            model.addAttribute("doctor",doctor );

        }
        else if (currentUser!=null &&currentUser.getPatient()!=null  && currentUser.getPatient().getId()!=null) {
            Long patientId = currentUser.getPatient().getId();
         Patient patient= patientBookingService.getPatientById(patientId);


            model.addAttribute("user",currentUser );
            model.addAttribute("Patient",patient);

        }
        else if (currentUser!=null && currentUser.getPatient()==null && currentUser.getDoctor()==null)  {

            model.addAttribute("user",currentUser );
        }
        return "Profile";
    }


}
