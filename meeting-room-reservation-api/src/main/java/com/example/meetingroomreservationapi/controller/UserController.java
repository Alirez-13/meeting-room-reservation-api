package com.example.meetingroomreservationapi.controller;

import com.example.meetingroomreservationapi.entity.User;
import com.example.meetingroomreservationapi.errHandler.NotFoundException;
import com.example.meetingroomreservationapi.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
@Tag(name = "User", description = "Operations related to users")
public class UserController {

    @Autowired
    UserService userService;

    // define @PostContruct for create Data in H2 DB
    @PostConstruct
    public void loadUser() {
        User user1 = new User(1, "Ali", "1234", "ADMIN");
        userService.saveUser(user1);

        User user2 = new User(2, "Narges", "1234", "MANAGER");
        userService.saveUser(user2);

        User user3 = new User(30, "Hanie", "1234", "USER");
        userService.saveUser(user3);
    }

    @GetMapping()
    @Operation(summary = "Get all users", description = "Retrieve a list of all users in the system")
    @ApiResponse(responseCode = "200", description = "List of users",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = User.class)))
    public ResponseEntity<List<User>> getAllUser() {
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }

    @GetMapping("/{userId}")
    @Operation(summary = "Get user by ID", description = "Retrieve a user by their ID")
    @ApiResponse(responseCode = "200", description = "User details",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = User.class)))
    @ApiResponse(responseCode = "404", description = "User not found")
    public ResponseEntity<User> getUserById(@PathVariable Long userId) {

        return new ResponseEntity<>(userService.findUserById(userId), HttpStatus.OK);
    }

    @PutMapping("/{userId}")
    @Operation(summary = "Update user by ID", description = "Update the details of a user by their ID")
    @ApiResponse(responseCode = "200", description = "Updated user details",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = User.class)))
    @ApiResponse(responseCode = "404", description = "User not found")
    public ResponseEntity<User> updateUserById(@PathVariable Long userId,User user) {
        return new ResponseEntity<>(userService.updateUser(user, userId), HttpStatus.OK);
    }

    @DeleteMapping("/{userId}")
    @Operation(summary = "Delete user by ID", description = "Delete a user from the system by their ID")
    @ApiResponse(responseCode = "200", description = "User deleted successfully")
    @ApiResponse(responseCode = "404", description = "User not found")
    public ResponseEntity<String> deleteUserById(@PathVariable long userId) {
        userService.deleteUserById(userId);
        return new ResponseEntity<>("User deleted successfully!", HttpStatus.OK);
    }
}