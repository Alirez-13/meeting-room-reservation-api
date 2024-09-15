package com.example.meetingroomreservationapi.controller;

import com.example.meetingroomreservationapi.entity.User;
import com.example.meetingroomreservationapi.errHandler.UserNotFoundException;
import com.example.meetingroomreservationapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    UserService userService;

    // define @PostContruct for create Data in H2 DB
    @PostConstruct
    public void loadUser() {
        User user1 = new User();
        user1.setFullName("Alireza Holghi");
        user1.setPassword("1234");
        user1.setRole("ADMIN");
        userService.saveUser(user1);

        User user2 = new User();
        user2.setFullName("Narges Niromand");
        user2.setPassword("1234");
        user2.setRole("MANAGER");
        userService.saveUser(user2);

        User user3 = new User();
        user3.setFullName("Hanie Ghavipanje");
        user3.setPassword("1234");
        user3.setRole("USER");
        userService.saveUser(user3);
    }

    @GetMapping()
    public List<User> getAllUser() {
        return userService.getAllUsers();
    }

    @GetMapping("/{userId}")
    public ResponseEntity<Optional<User>> getUserById(@PathVariable long userId) {
        // prevent query twice
        Optional<User> user = userService.findUserById(userId);
        if (!user.isPresent()) {
            throw new UserNotFoundException("User not found with Id: " + userId);
        }
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PutMapping()
    public ResponseEntity<User> updateUserById(User user) {

        if (!userService.findUserById(user.getId()).isPresent()) {
            throw new UserNotFoundException("User not found with Id: " + user.getId());
        }
        return new ResponseEntity<>(userService.updateUser(user), HttpStatus.OK);
    }
}
