package com.management.services;

import com.management.entities.Doctor;
import com.management.entities.Patient;
import com.management.entities.Role;
import com.management.entities.User;
import org.springframework.stereotype.Service;

@Service
public interface Userservice {
  void saveUser(String username,String email, String password, Patient patient);

  void saveAdmin(String username,String email, String password);

  void saveDocter(String username, String email, String password, Doctor doctor);

    Role save(String role);
    User loadUserByUsername(String username);
    void addRoleToUser(String username,String rolename,User user);

  void addRoleToADMIN(String username, String[] roleName, User user);
}
