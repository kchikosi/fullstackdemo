package com.dev.fullstackdemo.service;


import com.dev.fullstackdemo.domain.CustomUserDetails;
import com.dev.fullstackdemo.domain.request.SignInRequest;
import com.dev.fullstackdemo.domain.request.SignUpRequest;
import com.dev.fullstackdemo.domain.response.JwtAuthenticationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl {
    public String token;
    @Autowired
    private JwtUtilServiceImpl jwtService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserServiceImpl userService;

    public JwtAuthenticationResponse signup(SignUpRequest request) {
        CustomUserDetails user = new CustomUserDetails(request.getFirstname(), request.getLastname(), request.getUsername(), request.getPassword(), request.getEmail(), Arrays.asList("ROLE_USER"));
        userService.saveUser(user);
        String jwt = jwtService.generateToken(user);
        return JwtAuthenticationResponse.builder().token(jwt).build();
    }

    public JwtAuthenticationResponse signin(SignInRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        UserDetails user = null;
        try {
            user = userService.loadUserByUsername(request.getUsername());
        } catch (AuthenticationException e) {
            e.printStackTrace();
        }
        String jwt = jwtService.generateToken(user);
        return JwtAuthenticationResponse.builder().token(jwt).build();
    }
}
