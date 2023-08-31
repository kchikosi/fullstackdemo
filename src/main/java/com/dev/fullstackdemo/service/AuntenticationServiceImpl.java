package com.dev.fullstackdemo.service;


import com.dev.fullstackdemo.domain.CustomUserDetails;
import com.dev.fullstackdemo.domain.CustomUserDetailsRepository;
import com.dev.fullstackdemo.domain.request.SignInRequest;
import com.dev.fullstackdemo.domain.request.SignUpRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
@RequiredArgsConstructor
public class AuntenticationServiceImpl {
    public String token;
    private JwtServiceImpl jwtService;
    private CustomUserDetailsRepository userRepository;
    private AuthenticationManager authenticationManager;

    public String signup(SignUpRequest request) {
        CustomUserDetails user = new CustomUserDetails(request.getFirstName(), request.getLastName(), request.getUsername(), request.getPassword(), request.getEmail(), Arrays.asList("ROLE_USER"));

        userRepository.save(user);
        return jwtService.generateToken(user);
    }

    public String signin(SignInRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        CustomUserDetails user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new IllegalArgumentException("Invalid username"));
        return jwtService.generateToken(user);
    }
}
