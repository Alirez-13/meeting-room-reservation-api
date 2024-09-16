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
        // Check if the id already exists
        Optional<User> existingUser = userService.findUserById(newUser.getId());
        if (existingUser.isPresent()) {
            return new ResponseEntity<>("User id already exists.", HttpStatus.BAD_REQUEST);
        }

        // Save new user
        userService.saveUser(newUser);
        return new ResponseEntity<>("User registered successfully.", HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(User loginRequest) {
        Optional<User> user = userService.findUserById(loginRequest.getId());

        // Check if the user exists and the password is correct
        if (user.isPresent() && user.get().getPassword().equals(loginRequest.getPassword())) {
            // Generate a session ID
            String sessionId = UUID.randomUUID().toString();
            sessionStore.put(sessionId, user.get());

            return new ResponseEntity<>("Login successful. Session ID: " + sessionId, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Invalid username or password.", HttpStatus.UNAUTHORIZED);
        }
    }


    @PostMapping("/logout")
    public ResponseEntity<String> logout(@RequestHeader("sessionId") String sessionId) {
        // Remove user session
        if (sessionStore.containsKey(sessionId)) {
            sessionStore.remove(sessionId);
            return new ResponseEntity<>("Logout successful.", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Invalid session.", HttpStatus.UNAUTHORIZED);
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