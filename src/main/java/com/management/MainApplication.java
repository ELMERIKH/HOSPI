package com.management;


import com.management.entities.Patient;
import com.management.repositories.PatientRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.client.RestTemplate;

import java.util.Date;

@SpringBootApplication
public class MainApplication {
    public static void main(String[] args) {

        SpringApplication.run(MainApplication.class, args);}

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}