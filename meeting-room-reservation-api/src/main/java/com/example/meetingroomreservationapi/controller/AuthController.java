package com.example.meetingroomreservationapi.controller;

import com.example.meetingroomreservationapi.entity.User;
import com.example.meetingroomreservationapi.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Authentication", description = "User authentication operations")
public class AuthController {

    @Autowired
    private UserService userService;

    // Simple session storage
    private Map<String, User> sessionStore = new HashMap<>();

    @PostMapping("/signup")
    @Operation(summary = "Sign up a new user", description = "Register a new user in the system")
    @ApiResponse(responseCode = "201", description = "User registered successfully")
    @ApiResponse(responseCode = "400", description = "Invalid user details provided")
    public ResponseEntity<String> signup(User newUser) {
        // Save new user
        userService.saveUser(newUser);
        return new ResponseEntity<>("User registered successfully.", HttpStatus.CREATED);
    }

    @PostMapping("/login")
    @Operation(summary = "Log in a user", description = "Authenticate a user and generate a session ID")
    @ApiResponse(responseCode = "200", description = "Login successful, returns session ID")
    @ApiResponse(responseCode = "401", description = "Invalid username or password")
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
    @Operation(summary = "Get current user details", description = "Retrieve details of the currently logged-in user")
    @ApiResponse(responseCode = "200", description = "User details retrieved successfully",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = User.class)))
    @ApiResponse(responseCode = "401", description = "Invalid or missing session ID")
    public ResponseEntity<User> getCurrentUser(@RequestHeader("sessionId") String sessionId) {
        User user = sessionStore.get(sessionId);

        if (user != null) {
            return new ResponseEntity<>(user, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }
}