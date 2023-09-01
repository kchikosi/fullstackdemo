package com.dev.fullstackdemo.service;

public class AuthenticationException extends RuntimeException {
    public AuthenticationException(String username) {
        super("User [" + username + "] not found or password is invalid");
    }
}
