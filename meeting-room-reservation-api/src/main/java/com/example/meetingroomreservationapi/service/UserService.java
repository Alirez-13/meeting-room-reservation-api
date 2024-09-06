package com.example.meetingroomreservationapi.service;

import com.example.meetingroomreservationapi.entity.User;
import com.example.meetingroomreservationapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;


    public List<User> getUsers() {

        /*
        define a way to authenticate user role

            as example
            if(user.getRole == ADMIN){
                return userRepository.findAll();
            }

         */


        return userRepository.findAll();
    }
}
