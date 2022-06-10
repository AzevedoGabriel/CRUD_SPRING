package com.example.demo.controller;


import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping(value="/api")
public class Controller {

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody User user) {

        User newUser = userRepository.save(user);

        return ResponseEntity.ok(newUser);
    }

    @GetMapping("/users")
    public ResponseEntity<?> getAllUsers() {
        List<User> users = userRepository.findAll();

        return ResponseEntity.ok(users);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        User user = userRepository.findById(id).get();

        return ResponseEntity.ok(user);
    }

    @PutMapping("/user/{id}")
    public ResponseEntity<?> update(@PathVariable Long id,@RequestBody User userRequest) {
        Optional<User> opt = userRepository.findById(id);
        User user = opt.get();
        user.setFirstName(userRequest.getFirstName());
        user.setLastName(userRequest.getLastName());
        user.setCidade(userRequest.getCidade());
        user.setTelefone(userRequest.getTelefone());
        userRepository.save(user);
        return ResponseEntity.ok(user);
    }

    @DeleteMapping("/delete-user/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        User user = userRepository.findById(id).get();
        userRepository.delete(user);
        return ResponseEntity.ok(true);
    }
}
