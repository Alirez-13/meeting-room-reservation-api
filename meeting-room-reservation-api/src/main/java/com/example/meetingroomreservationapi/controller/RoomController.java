package com.example.meetingroomreservationapi.controller;

import com.example.meetingroomreservationapi.entity.Room;
import com.example.meetingroomreservationapi.entity.User;
import com.example.meetingroomreservationapi.errHandler.NotFoundException;
import com.example.meetingroomreservationapi.service.RoomService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/rooms")
@Tag(name = "Room", description = "Operations related to rooms")
public class RoomController {

    @Autowired
    private RoomService roomService;

    @GetMapping
    @Operation(summary = "Get all rooms", description = "Retrieve a list of all rooms")
    @ApiResponse(responseCode = "200", description = "List of all rooms",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Room.class)))
    public ResponseEntity<List<Room>> getAllRooms() {
        return new ResponseEntity<>(roomService.getAllRooms(), HttpStatus.OK);
    }

    @PostMapping
    @Operation(summary = "Create a new room", description = "Add a new room to the system")
    @ApiResponse(responseCode = "201", description = "Room created successfully",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Room.class)))
    @ApiResponse(responseCode = "400", description = "Invalid room details provided")
    public ResponseEntity<Room> createRoom(@RequestBody Room room) {
        return new ResponseEntity<>(roomService.createRoom(room), HttpStatus.CREATED);
    }

    @DeleteMapping("/{roomId}")
    @Operation(summary = "Delete a room", description = "Remove a room from the system by its ID")
    @ApiResponse(responseCode = "200", description = "Room deleted successfully")
    @ApiResponse(responseCode = "404", description = "Room not found")
    public ResponseEntity<String> deleteRoom(@PathVariable Long roomId) {
        roomService.deleteRoomById(roomId);
        return new ResponseEntity<>("Room deleted successfully!", HttpStatus.OK);
    }

    @PutMapping("/{roomId}")
    @Operation(summary = "Update a room", description = "Update the details of an existing room by its ID")
    @ApiResponse(responseCode = "200", description = "Room updated successfully",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Room.class)))
    @ApiResponse(responseCode = "404", description = "Room not found")
    public ResponseEntity<Room> updateRoom(@PathVariable Long roomId, @RequestBody Room room) {
        return new ResponseEntity<>(roomService.updateRoom(roomId, room), HttpStatus.OK);
    }

    @GetMapping("/empty")
    @Operation(summary = "Get empty rooms", description = "Retrieve a list of rooms that are currently empty")
    @ApiResponse(responseCode = "200", description = "List of empty rooms",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Room.class)))
    public ResponseEntity<List<Room>> getEmptyRooms() {
        return new ResponseEntity<>(roomService.getEmptyRooms(), HttpStatus.OK);
    }
}