package com.dev.fullstackdemo.web;

import com.dev.fullstackdemo.domain.CustomUserDetails;
import com.dev.fullstackdemo.domain.CustomUserDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/users")
public class CustomUserController {
    @Autowired
    private CustomUserDetailsRepository repository;

    public CustomUserController() {
    }

    public CustomUserController(CustomUserDetailsRepository repository) {
        this.repository = repository;
    }

    @GetMapping("")
    public ResponseEntity<List<CustomUserDetails>> all() {
        return new ResponseEntity<List<CustomUserDetails>>((List<CustomUserDetails>) repository.findAll(), null, HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<CustomUserDetails> save(@RequestBody CustomUserDetails user) {
        return new ResponseEntity<CustomUserDetails>(repository.save(user), null, HttpStatus.CREATED);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<CustomUserDetails> get(@PathVariable("id") long id) {
        CustomUserDetails customUserDetails = repository.findById(id).orElseThrow(() -> new CustomUserNotFoundException(id));
        return new ResponseEntity<CustomUserDetails>(customUserDetails, null, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomUserDetails> update(@PathVariable("id") long id) {
        CustomUserDetails customUserDetails = repository.findById(id).orElseThrow(() -> new CustomUserNotFoundException(id));
        return new ResponseEntity<>(repository.save(customUserDetails), null, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable("id") long id) {
        CustomUserDetails customUserDetails = repository.findById(id).orElseThrow(() -> new CustomUserNotFoundException(id));
        repository.delete(customUserDetails);
        return new ResponseEntity<CustomUserDetails>(HttpStatus.OK);
    }

}
