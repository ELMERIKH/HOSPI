package com.management.services;

import com.management.entities.Doctor;
import com.management.entities.Patient;
import com.management.entities.Role;
import com.management.entities.User;
import com.management.repositories.DoctorRepository;
import com.management.repositories.PatientRepository;
import com.management.repositories.RoleRepository;
import com.management.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Transactional
public class UserserviceImpl implements Userservice {
@Autowired
private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
@Autowired
private PatientRepository patientRepository;
    @Autowired
    private DoctorRepository doctorRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Override
    public void saveUser(String username,String email, String password, Patient patient) {

        User user=new User();

        user.setId(UUID.randomUUID().toString());
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(password));
        user.setUsername(username);



        user.setPatient(patient);
        addRoleToUser(username,"PATIENT",user);
        patientRepository.save(patient);
        userRepository.save(user);

    };
    @Override
    public void saveAdmin(String username,String email, String password) {

        User user=new User();

        user.setId(UUID.randomUUID().toString());
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(password));
        user.setUsername(username);




        addRoleToADMIN(username, new String[]{"ADMIN", "DOCTOR","PATIENT"},user);

        userRepository.save(user);

    };

    @Override
    public void saveDocter(String username,String email, String password, Doctor doctor) {
        User user=new User();

        user.setId(UUID.randomUUID().toString());
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(password));
        user.setUsername(username);



        user.setDoctor(doctor);
       addRoleToUser(username,"DOCTOR",user);
        doctorRepository.save(doctor);
         userRepository.save(user);

    }

    @Override
    public Role save(String role) {

Role role1=new Role(role);


        return roleRepository.save(role1);
    }

    @Override
    public User loadUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public void addRoleToUser(String username, String roleName, User user) {







        Role role =save(roleName);

        user.getRoles().add(role);


        roleRepository.save(role);
    }
    @Override
    public void addRoleToADMIN(String username, String[] roleName, User user) {





       for(String r : roleName){

        Role role =new Role(r);

        user.getRoles().add(role);
           roleRepository.save(role);
       }


    }
}
