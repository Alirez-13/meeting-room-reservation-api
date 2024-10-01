package com.example.meetingroomreservationapi.service;

import com.example.meetingroomreservationapi.entity.User;
import com.example.meetingroomreservationapi.errHandler.NotFoundException;
import com.example.meetingroomreservationapi.errHandler.UnathorizeException;
import com.example.meetingroomreservationapi.repository.UserRepository;
<<<<<<< Updated upstream
=======
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
>>>>>>> Stashed changes
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;


    public List<User> getAllUsers() {
        //   define a way to authenticate user role
<<<<<<< Updated upstream
        List<User> usersList = userRepository.findAll();
        if (usersList.isEmpty()) {
=======
//        logger.info("Fetching all users");
        List<User> usersList = userRepository.findAll();
        if (usersList.isEmpty()) {
//            logger.warn("User list is empty!");
>>>>>>> Stashed changes
            throw new NotFoundException("user list is empty !");
        }
        return usersList;
    }

<<<<<<< Updated upstream
    public Optional<User> findUserById(Long userId) {
        Optional<User> user = userRepository.findById(userId);

        if (!user.isPresent()){
            throw new NotFoundException("User not found with Id: " + userId);
        }
        return user;
    }

    public void saveUser(User user) {
=======
    public User findUserById(Long userId) {
//        logger.info("Finding user with ID: {}", userId);
        return userRepository.findById(userId)
                .orElseThrow(() -> {
//                    logger.error("User not found with ID: {}", userId);
                    return new NotFoundException("User not found with Id: " + userId);
                });
    }

    public void saveUser(User user) {
//        logger.info("Saving user with ID: {}", user.getId());
>>>>>>> Stashed changes
        userRepository.save(user);
    }

    public void deleteUserById(Long userId) {
<<<<<<< Updated upstream

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
=======
//        logger.info("Deleting user with ID: {}", userId);
        User user = userRepository.findById(userId)
                .orElseThrow(() -> {
//                    logger.error("User not found with ID: {}", userId);
                    return new NotFoundException("User not found with id: " + userId);
                });
        userRepository.deleteById(userId);
    }

    public User updateUser(User updatedUser, Long id) {
//        logger.info("Updating user with ID: {}", id);
        return userRepository.findById(id)
                .map(user ->{
                    user.setPassword(updatedUser.getPassword());
                    user.setFullName(updatedUser.getFullName());
                    return userRepository.save(user);
                })
                .orElseThrow(() -> {
//                    logger.error("User not found with ID: {}", id);
                    return new NotFoundException("User not found with id: " + id);
                });
>>>>>>> Stashed changes
    }
}