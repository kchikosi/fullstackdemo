package com.dev.fullstackdemo.web;

public class CustomUserNotFoundException extends RuntimeException {
    public CustomUserNotFoundException(long id) {
        super("User with id [" + id + "] not found");
    }
}
