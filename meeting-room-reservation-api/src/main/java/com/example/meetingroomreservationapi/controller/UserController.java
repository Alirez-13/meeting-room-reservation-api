package com.example.meetingroomreservationapi.controller;

import com.example.meetingroomreservationapi.entity.User;
import com.example.meetingroomreservationapi.errHandler.NotFoundException;
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
        User user1 = new User(1, "Ali", "1234", "ADMIN");
        userService.saveUser(user1);

        User user2 = new User(2, "Narges", "1234", "MANAGER");
        userService.saveUser(user2);

        User user3 = new User(30, "Hanie", "1234", "USER");
        userService.saveUser(user3);
    }

    @GetMapping()
    public ResponseEntity<List<User>> getAllUser() {
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<Optional<User>> getUserById(@PathVariable long userId) {

        return new ResponseEntity<>(userService.findUserById(userId), HttpStatus.OK);
    }

    @PutMapping()
    public ResponseEntity<User> updateUserById(User user) {
        return new ResponseEntity<>(userService.updateUser(user), HttpStatus.OK);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<String> deleteUserById(@PathVariable long userId) {
        userService.deleteUserById(userId);
        return new ResponseEntity<>("User deleted successfully!", HttpStatus.OK);
    }
}
