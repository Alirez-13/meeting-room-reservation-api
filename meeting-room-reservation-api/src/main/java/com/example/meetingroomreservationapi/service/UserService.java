package com.example.meetingroomreservationapi.service;

import com.example.meetingroomreservationapi.entity.User;
import com.example.meetingroomreservationapi.errHandler.UserNotFoundException;
import com.example.meetingroomreservationapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;


    public List<User> getUsers(User user) {
        /*
            define a way to authenticate user role
         */
        return userRepository.findAll();
    }

    public Optional<User> findUserById(Long id) {
        return userRepository.findById(id);
    }

    public void addUser(User user) {
        user.setFullName(user.getFullName());
        user.setPassword(user.getPassword());
        user.setRole(user.getRole());

        userRepository.save(user);
    }


}
