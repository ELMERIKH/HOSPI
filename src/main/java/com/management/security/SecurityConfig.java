package com.management.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity

public class SecurityConfig {



    @Autowired
    private PasswordEncoder passwordEncoder;

//@Bean
public UserDetailsService userDetailsService(){
    return  new UserDetailsService() {
        @Override
        public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
            return null;
        }
    };
}
@Bean
public JdbcUserDetailsManager jdbcUserDetailsManager(DataSource dataSource){

    return new JdbcUserDetailsManager(dataSource);
}
    //@Bean
    public InMemoryUserDetailsManager inMemoryUserDetailsManager(){
        return new InMemoryUserDetailsManager(
        User.withUsername("user1").password(passwordEncoder.encode("123")).roles("USER").build(),
        User.withUsername("ADMIN").password(passwordEncoder.encode("12345")).roles("USER","ADMIN","DOCTER").build(),
                User.withUsername("docter").password(passwordEncoder.encode("00123")).roles("DOCTER").build()
        );
    }
    @Bean
public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws  Exception{


        httpSecurity.formLogin().loginPage("/login").defaultSuccessUrl("/")
                .permitAll()
                .and()
                .logout()
                .permitAll();;
    httpSecurity.authorizeHttpRequests().requestMatchers("/user/**").hasRole("USER");
        httpSecurity.authorizeHttpRequests().requestMatchers("/admin/**").hasRole("ADMIN");
        httpSecurity.authorizeHttpRequests().requestMatchers("/doctors/**").hasRole("DOCTOR");

        httpSecurity.authorizeHttpRequests().anyRequest().authenticated();
        httpSecurity.exceptionHandling().accessDeniedPage("/notAuthorized");
        httpSecurity.rememberMe();
    return  httpSecurity.build();
}
}
