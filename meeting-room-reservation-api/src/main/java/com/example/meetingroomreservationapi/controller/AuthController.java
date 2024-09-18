package com.example.meetingroomreservationapi.controller;

import com.example.meetingroomreservationapi.entity.User;
import com.example.meetingroomreservationapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    // Simple session storage
    private Map<String, User> sessionStore = new HashMap<>();

    @PostMapping("/signup")
    public ResponseEntity<String> signup(User newUser) {
        // Save new user
        userService.saveUser(newUser);
        return new ResponseEntity<>("User registered successfully.", HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(User loginRequest) {
        User user = userService.findUserById(loginRequest.getId());

        // Check if the user exists and the password is correct
        if ( user.getId()>0 && user.getPassword().equals(loginRequest.getPassword())) {
            // Generate a session ID
            String sessionId = UUID.randomUUID().toString();
            sessionStore.put(sessionId, user);

            return new ResponseEntity<>("Login successful. Session ID: " + sessionId, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Invalid username or password.", HttpStatus.UNAUTHORIZED);
        }
    }


    @GetMapping("/current-user")
    public ResponseEntity<User> getCurrentUser(@RequestHeader("sessionId") String sessionId) {
        User user = sessionStore.get(sessionId);

        if (user != null) {
            return new ResponseEntity<>(user, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }
}