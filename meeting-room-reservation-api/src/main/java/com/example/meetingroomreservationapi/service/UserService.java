package com.example.meetingroomreservationapi.service;

import com.example.meetingroomreservationapi.entity.User;
import com.example.meetingroomreservationapi.errHandler.NotFoundException;
import com.example.meetingroomreservationapi.errHandler.UnathorizeException;
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
        //   define a way to authenticate user role

        return userRepository.findAll();
    }

    public Optional<User> findUserById(Long userId) {
        return userRepository.findById(userId);
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
