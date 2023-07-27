package com.dev.fullstackdemo.web;

import com.dev.fullstackdemo.domain.CustomUser;
import com.dev.fullstackdemo.domain.CustomUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/users")
public class CustomUserController {
    @Autowired
    private CustomUserRepository repository;

    public CustomUserController() {
    }

    public CustomUserController(CustomUserRepository repository) {
        this.repository = repository;
    }

    @GetMapping("")
    public ResponseEntity<List<CustomUser>> all() {
        return new ResponseEntity<List<CustomUser>>((List<CustomUser>) repository.findAll(), null, HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<CustomUser> save(@RequestBody CustomUser user) {
        return new ResponseEntity<CustomUser>(repository.save(user), null, HttpStatus.CREATED);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<CustomUser> get(@PathVariable("id") long id) {
        CustomUser customUser = repository.findById(id).orElseThrow(() -> new CustomUserNotFoundException(id));
        return new ResponseEntity<CustomUser>(customUser, null, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomUser> update(@PathVariable("id") long id) {
        CustomUser customUser = repository.findById(id).orElseThrow(() -> new CustomUserNotFoundException(id));
        return new ResponseEntity<>(repository.save(customUser), null, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable("id") long id) {
        CustomUser customUser = repository.findById(id).orElseThrow(() -> new CustomUserNotFoundException(id));
        repository.delete(customUser);
        return new ResponseEntity<CustomUser>(HttpStatus.OK);
    }

}
