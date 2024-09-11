package com.example.meetingroomreservationapi.controller;

import com.example.meetingroomreservationapi.entity.User;
import com.example.meetingroomreservationapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api")
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
        userService.addUser(user1);

        User user2 = new User();
        user2.setFullName("Narges Niromand");
        user2.setPassword("1234");
        user2.setRole("MANAGER");
        userService.addUser(user2);


        User user3 = new User();
        user3.setFullName("Hanie Ghavipanje");
        user3.setPassword("1234");
        user3.setRole("USER");
        userService.addUser(user3);
    }

    @GetMapping("/users")
    public List<User> getAllUser(User user) {

        return userService.getUsers(user);
    }
}
