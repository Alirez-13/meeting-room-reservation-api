package com.example.meetingroomreservationapi.service;

import com.example.meetingroomreservationapi.entity.User;
import com.example.meetingroomreservationapi.errHandler.NotFoundException;
import com.example.meetingroomreservationapi.errHandler.UnathorizeException;
import com.example.meetingroomreservationapi.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    public List<User> getAllUsers() {
        //   define a way to authenticate user role
        logger.info("Fetching all users");
        List<User> usersList = userRepository.findAll();
        if (usersList.isEmpty()) {
            logger.warn("User list is empty!");
            throw new NotFoundException("user list is empty !");
        }
        return usersList;
    }

    public User findUserById(Long userId) {
        logger.info("Finding user with ID: {}", userId);
        return userRepository.findById(userId)
                .orElseThrow(() -> {
                    logger.error("User not found with ID: {}", userId);
                    return new NotFoundException("User not found with Id: " + userId);
                });
    }

    public void saveUser(User user) {
        logger.info("Saving user with ID: {}", user.getId());
        userRepository.save(user);
    }

    public void deleteUserById(Long userId) {
        logger.info("Deleting user with ID: {}", userId);
        User user = userRepository.findById(userId)
                .orElseThrow(() -> {
                    logger.error("User not found with ID: {}", userId);
                    return new NotFoundException("User not found with id: " + userId);
                });
        userRepository.deleteById(userId);
    }

    public User updateUser(User updatedUser, Long id) {
        logger.info("Updating user with ID: {}", id);
        return userRepository.findById(id)
                .map(user ->{
                    user.setPassword(updatedUser.getPassword());
                    user.setFullName(updatedUser.getFullName());
                    return userRepository.save(user);
                })
                .orElseThrow(() -> {
                    logger.error("User not found with ID: {}", id);
                    return new NotFoundException("User not found with id: " + id);
                });
    }
}