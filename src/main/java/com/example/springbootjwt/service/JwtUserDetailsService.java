package com.example.springbootjwt.service;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

/**
 * JWTUserDetailsService implements the Spring Security UserDetailsService interface.
 * It overrides the loadUserByUsername for fetching user details from the database using the username.
 * The Spring Security Authentication Manager calls this method for getting the user details
 * from the database when authenticating the user details provided by the user.
 * Here we are getting the user details from a hardcoded User List.
 * Also the password for a user is stored in encrypted format using BCrypt.
 * Previously we have seen Spring Boot Security - Password Encoding Using Bcrypt.
 * Here using the Online Bcrypt Generator you can generate the Bcrypt for a password.
 */

@Service
public class JwtUserDetailsService implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        //todo: will be adding the DAO implementation for fetching User Details from the Database.
        if ("javainuse".equals(userName)) {
            return new User("javainuse", "$2a$10$slYQmyNdGzTn7ZLBXBChFOC9f6kFjAqPhccnP6DxlWXx2lPk1C3G6",
                    new ArrayList<>());
        } else {
            throw new UsernameNotFoundException("User not found with username: " + userName);
        }
    }
}
