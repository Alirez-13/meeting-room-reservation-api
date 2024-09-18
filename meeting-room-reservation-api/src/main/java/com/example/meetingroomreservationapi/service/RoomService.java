package com.example.meetingroomreservationapi.service;

import com.example.meetingroomreservationapi.entity.Room;
import com.example.meetingroomreservationapi.errHandler.NotFoundException;
import com.example.meetingroomreservationapi.errHandler.RoomOccupiedException;
import com.example.meetingroomreservationapi.repository.RoomRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoomService {

    @Autowired
    private RoomRepository roomRepository;

    private static final Logger logger = LoggerFactory.getLogger(RoomService.class);
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
                .orElseThrow(() -> {
                    String errorMessage = "Room with ID " + id + " not found.";
                    logger.error(errorMessage);
                    return new NotFoundException(errorMessage);
                });
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

    public boolean isRoomEmpty(long roomId) {
        logger.info("Checking if room with ID: {} is empty", roomId);
        Room room = roomRepository.findById(roomId)
                .orElseThrow(() -> {
                    logger.error("Room with ID: {} not found!", roomId);
                    return new NotFoundException("Room with ID: " + roomId + " not found!");
                });
        return room.isEmpty();
    }

    public void updateRoomStatus(long roomId) {
        logger.info("Updating room status for room with ID: {}", roomId);
        Room room = roomRepository.findById(roomId).map(
                        room1 -> {
                            room1.setEmpty(false);
                            return roomRepository.save(room1);
                        }
                )
                .orElseThrow(() -> {
                    logger.error("Room with ID: {} not found!", roomId);
                    return new NotFoundException("Room with ID: " + roomId + " not found!");
                });
        logger.info("Room status for room with ID: {} updated successfully", roomId);
    }
}