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

    public User findUserById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("User not found with Id: " + userId));
    }

    public void saveUser(User user) {
        userRepository.save(user);
    }

    public void deleteUserById(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("User not found with id :"+userId ));
        userRepository.deleteById(userId);
    }

    public User updateUser(User updatedUser, Long id) {
        return userRepository.findById(id)
                .map(user ->{
                    user.setPassword(updatedUser.getPassword());
                    user.setFullName(updatedUser.getFullName());
                    return userRepository.save(user);
                })
                .orElseThrow(() -> new NotFoundException("User not found with id :"+ id));
    }
}