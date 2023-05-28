package com.management;


import com.management.entities.Doctor;
import com.management.entities.Patient;
import com.management.repositories.PatientRepository;
import com.management.services.UserDetailServicesImpl;
import com.management.services.UserserviceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.web.client.RestTemplate;

import java.util.Date;

@SpringBootApplication
public class MainApplication {
    public static void main(String[] args) {

        SpringApplication.run(MainApplication.class, args);}

//@Bean
CommandLineRunner commandLineRunner(JdbcUserDetailsManager jdbcUserDetailsManager){
        PasswordEncoder passwordEncoder =passwordEncoder();
        return args -> {
            UserDetails u1=jdbcUserDetailsManager.loadUserByUsername("moncef");
            if(u1==null){
            jdbcUserDetailsManager.createUser(
                    User.withUsername("moncef").password(passwordEncoder().encode("123")).authorities("USER").build());}
                UserDetails u2=jdbcUserDetailsManager.loadUserByUsername("admin");
            if(u2==null){
            jdbcUserDetailsManager.createUser(
                    User.withUsername("admin").password(passwordEncoder().encode("123"))
                            .authorities("ADMIN","USER","DOCTOR").build());}
                UserDetails u3=jdbcUserDetailsManager.loadUserByUsername("DRmoncef");
                if(u3==null){
            jdbcUserDetailsManager.createUser(
                    User.withUsername("DRmoncef").password(passwordEncoder().encode("123"))
                            .authorities("DOCTOR").build());

                }

        };
}
    @Bean
    CommandLineRunner commandLineRunner(UserserviceImpl userservice){
        PasswordEncoder passwordEncoder =passwordEncoder();
        return args -> {

            com.management.entities.User u1= userservice.loadUserByUsername("admin");
            if (u1==null){
            userservice.saveAdmin("admin","admin@gmail.com", "12345");

            }
            com.management.entities.User u2= userservice.loadUserByUsername("DRmoncef");
            if (u2==null){
                Doctor doctor=new Doctor("DRmoncef","moncef@gmail.com");
                userservice.saveDocter("DRmoncef","moncef@gmail.com", "12345", doctor);

            }
            com.management.entities.User u3= userservice.loadUserByUsername("patient1");
            if (u3==null){
                Patient patient=new Patient("patient1","mmmmmm@gmail.com");
                userservice.saveUser("patient1","mmmmmm@gmail.com", "12345", patient);

            }



        };
    }
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }


}