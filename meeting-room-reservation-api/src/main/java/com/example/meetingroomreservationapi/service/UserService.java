package com.example.meetingroomreservationapi.service;

import com.example.meetingroomreservationapi.entity.User;
import com.example.meetingroomreservationapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;


    public List<User> getAllUsers() {
        /*
            define a way to authenticate user role
         */
        return userRepository.findAll();
    }

    public Optional<User> findUserById(Long userId) {
        return userRepository.findById(userId);
    }

    public void saveUser(User user) {
        user.setFullName(user.getFullName());
        user.setPassword(user.getPassword());
        user.setRole(user.getRole());

        userRepository.save(user);
    }

    public void deleteUserById(Long userId) {
       userRepository.deleteById(userId);
    }

    public User updateUser(User user) {
        if (Objects.equals(user.getRole(), "ADMIN") || Objects.equals(user.getRole(), "MANAGER")) {
            user.setRole(user.getRole());
        }
        return userRepository.save(user);
    }
}
