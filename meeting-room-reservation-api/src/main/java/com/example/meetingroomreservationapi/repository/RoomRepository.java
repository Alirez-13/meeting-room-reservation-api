package com.example.meetingroomreservationapi.repository;

import com.example.meetingroomreservationapi.entity.Room;
import com.example.meetingroomreservationapi.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {
    List<Room> findAll();
    List<Room> findByIsEmptyTrue();
}