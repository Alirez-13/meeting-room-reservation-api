package com.example.meetingroomreservationapi.service;

import com.example.meetingroomreservationapi.entity.Room;
import com.example.meetingroomreservationapi.errHandler.NotFoundException;
import com.example.meetingroomreservationapi.repository.RoomRepository;
import com.example.meetingroomreservationapi.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RoomService {
    private static final Logger logger = LoggerFactory.getLogger(RoomService.class);
    @Autowired
    private RoomRepository roomRepository;

    public List<Room> getAllRooms() {
        logger.debug("Fetching all rooms");
        return roomRepository.findAll();
    }

    public Room createRoom(Room room) {
        logger.debug("Creating room: {}", room);
        return roomRepository.save(room);
    }

    public void deleteRoomById(Long id) {
        logger.debug("Deleting room with ID: {}", id);
        Room room = roomRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Room with ID " + id + " not found."));
        roomRepository.deleteById(id);
        logger.info("Room with ID {} deleted successfully", id);
    }

    public Room updateRoom(Long id, Room updatedRoom) {
        logger.debug("Updating room with ID: {}", id);
        return roomRepository.findById(id)
                .map(room -> {
                    room.setRoomCapacity(updatedRoom.getRoomCapacity());
                    room.setEmpty(updatedRoom.isEmpty());
                    logger.info("Room with ID {} updated successfully", id);
                    return roomRepository.save(room);
                })
                .orElseThrow(() -> {
                    String errorMessage = "Room not found with id: " + id;
                    logger.error(errorMessage);
                    return new NotFoundException(errorMessage);
                });
    }

    public List<Room> getEmptyRooms() { //available
        logger.debug("Fetching empty rooms");
        return roomRepository.findByIsEmptyTrue();
    }
}
