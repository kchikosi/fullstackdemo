package com.dev.fullstackdemo.service;


import com.dev.fullstackdemo.domain.CustomUser;
import com.dev.fullstackdemo.domain.CustomUserRepository;
import com.dev.fullstackdemo.domain.SignInRequest;
import com.dev.fullstackdemo.domain.SignUpRequest;
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
    private CustomUserRepository userRepository;
    private AuthenticationManager authenticationManager;

    public String signup(SignUpRequest request) {
        CustomUser user = new CustomUser(request.getFirstName(), request.getLastName(), request.getEmail(), request.getPassword(), Arrays.asList("ROLE_USER"));
        userRepository.save(user);
        return jwtService.generateToken(user);
    }

    public String signin(SignInRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        CustomUser user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new IllegalArgumentException("Invalid username"));
        return jwtService.generateToken(user);
    }
}
