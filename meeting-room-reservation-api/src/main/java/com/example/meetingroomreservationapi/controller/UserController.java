package com.example.meetingroomreservationapi.controller;

import com.example.meetingroomreservationapi.entity.User;
import com.example.meetingroomreservationapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/users")
    public List<User> getAllUser(User user) {
        if (Objects.equals(user.getRole(), "ADMIN")) {
            return userService.getUsers(user);
        }
        return null;
    }
}
