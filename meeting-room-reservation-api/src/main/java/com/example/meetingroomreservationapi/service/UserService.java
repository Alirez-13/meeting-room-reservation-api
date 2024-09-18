package com.example.meetingroomreservationapi.service;

import com.example.meetingroomreservationapi.entity.User;
import com.example.meetingroomreservationapi.errHandler.NotFoundException;
import com.example.meetingroomreservationapi.errHandler.UnathorizeException;
import com.example.meetingroomreservationapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;


    public List<User> getAllUsers() {
        //   define a way to authenticate user role
        List<User> usersList = userRepository.findAll();
        if (usersList.isEmpty()) {
            throw new NotFoundException("user list is empty !");
        }
        return usersList;
    }

    public Optional<User> findUserById(Long userId) {
        Optional<User> user = userRepository.findById(userId);

        if (!user.isPresent()){
            throw new NotFoundException("User not found with Id: " + userId);
        }
        return user;
    }

    public void saveUser(User user) {
        userRepository.save(user);
    }

    public void deleteUserById(Long userId) {

        User user = userRepository.getReferenceById(userId);

        if (Objects.equals(user.getRole(), "ADMIN") || Objects.equals(user.getRole(), "MANAGER")) {
            userRepository.deleteById(userId);
        } else if (user.getId() == 0) {
            throw new NotFoundException("user not found!");
        } else {
            throw new UnathorizeException("You're not allowed be here!");
        }
    }

    public User updateUser(User user) {

        if (findUserById(user.getId()).isPresent()) {
            return userRepository.save(user);
        }
        throw new NotFoundException("User not found with Id: " + user.getId());
    }
}