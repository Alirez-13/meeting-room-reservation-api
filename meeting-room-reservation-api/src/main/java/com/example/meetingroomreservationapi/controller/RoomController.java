package com.example.meetingroomreservationapi.controller;

import com.example.meetingroomreservationapi.entity.Room;
import com.example.meetingroomreservationapi.entity.User;
import com.example.meetingroomreservationapi.errHandler.NotFoundException;
import com.example.meetingroomreservationapi.service.RoomService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/rooms")

public class RoomController {

    @Autowired
    private RoomService roomService;

    @GetMapping
    public ResponseEntity<List<Room>> getAllRooms() {
        return new ResponseEntity<>(roomService.getAllRooms(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Room> createRoom(@RequestBody Room room) {
        return new ResponseEntity<>(roomService.createRoom(room), HttpStatus.CREATED);
    }

    @DeleteMapping("/{roomId}")
    public ResponseEntity<String> deleteRoom(@PathVariable Long roomId) {
        roomService.deleteRoomById(roomId);
        return new ResponseEntity<>("Room deleted successfully!", HttpStatus.OK);
    }

    @PutMapping("/{roomId}")
    public ResponseEntity<Room> updateRoom(@PathVariable Long roomId, @RequestBody Room room) {
        return new ResponseEntity<>(roomService.updateRoom(roomId, room), HttpStatus.OK);
    }

    @GetMapping("/empty")
    public ResponseEntity<List<Room>> getEmptyRooms() {
        return new ResponseEntity<>(roomService.getEmptyRooms(), HttpStatus.OK);
    }
}
