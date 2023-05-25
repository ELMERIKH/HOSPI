package com.management.services;

import com.management.entities.Role;
import com.management.entities.User;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserDetailServicesImpl implements UserDetailsService {
UserserviceImpl userservice;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user =userservice.loadUserByUsername(username);
        if(username==null)throw  new UsernameNotFoundException("not found");

        return org.springframework.security.core.userdetails.User
                .withUsername(username).password(user.getPassword()).roles(user.getRoles().stream().map(Role::getRoleName).toArray(String[]::new))
                .build();
    }
}
