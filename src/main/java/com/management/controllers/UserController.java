package com.management.controllers;

import com.management.entities.Doctor;
import com.management.entities.Patient;
import com.management.entities.User;
import com.management.repositories.UserRepository;
import com.management.services.UserDetailServicesImpl;
import com.management.services.Userservice;
import com.management.services.UserserviceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


@Controller
    public class UserController {
        @Autowired
        private UserserviceImpl Userservice;
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




            return new ModelAndView("redirect:/user/home");
        }
    @GetMapping("/doctors/register")
    public ModelAndView showRegistrationFormD() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("RegisterDoc");
        modelAndView.addObject("doctor", new Doctor());

        return modelAndView;
    }
    @PostMapping("/doctors/register")
    public ModelAndView registerDoctor(@ModelAttribute Doctor doctor,@RequestParam String username,@RequestParam String email, @RequestParam String password, @RequestParam String confirmedPassword) {

        Userservice.saveDocter(username,email, password,doctor);




        return new ModelAndView("redirect:/user/home");
    }
    @GetMapping("/getid")
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

}
