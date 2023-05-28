package com.management.repositories;

import com.management.entities.Doctor;
import com.management.entities.User;
import jakarta.jws.soap.SOAPBinding;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
   User findByUsername(String username);


}
