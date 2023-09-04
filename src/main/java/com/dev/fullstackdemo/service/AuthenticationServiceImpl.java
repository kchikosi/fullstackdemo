package com.dev.fullstackdemo.service;


import com.dev.fullstackdemo.domain.CustomUserDetails;
import com.dev.fullstackdemo.domain.CustomUserDetailsRepository;
import com.dev.fullstackdemo.domain.request.SignInRequest;
import com.dev.fullstackdemo.domain.request.SignUpRequest;
import com.dev.fullstackdemo.domain.response.JwtAuthenticationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl {
    public String token;
    @Autowired
    private JwtUtilServiceImpl jwtService;
    @Autowired
    private CustomUserDetailsRepository userRepository;
    @Autowired
    private AuthenticationManager authenticationManager;

    public JwtAuthenticationResponse signup(SignUpRequest request) {
        CustomUserDetails user = new CustomUserDetails(request.getFirstname(), request.getLastname(), request.getUsername(), request.getPassword(), request.getEmail(), Arrays.asList("ROLE_USER"));
        userRepository.save(user);
        String jwt = jwtService.generateToken(user);
        return JwtAuthenticationResponse.builder().token(jwt).build();
    }

    public JwtAuthenticationResponse signin(SignInRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        CustomUserDetails user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new AuthenticationException(request.getUsername()));
        String jwt = jwtService.generateToken(user);
        return JwtAuthenticationResponse.builder().token(jwt).build();
    }
}
