package com.example.meetingroomreservationapi.repository;

import com.example.meetingroomreservationapi.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User , Long> {
    List<User> findAll();
}