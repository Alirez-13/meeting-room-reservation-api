package com.example.meetingroomreservationapi.service;

import com.example.meetingroomreservationapi.entity.Room;
import com.example.meetingroomreservationapi.errHandler.NotFoundException;
import com.example.meetingroomreservationapi.repository.RoomRepository;
<<<<<<< Updated upstream
import com.example.meetingroomreservationapi.repository.UserRepository;
=======
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
>>>>>>> Stashed changes
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RoomService {

    @Autowired
    private RoomRepository roomRepository;

<<<<<<< Updated upstream
    public List<Room> getAllRooms() {
=======
//    private static final Logger logger = LoggerFactory.getLogger(RoomService.class);

    public List<Room> getAllRooms() {

//        logger.debug("Fetching all rooms");
>>>>>>> Stashed changes
        return roomRepository.findAll();
    }

    public Room createRoom(Room room) {
<<<<<<< Updated upstream
=======

//        logger.debug("Creating room: {}", room);
>>>>>>> Stashed changes
        return roomRepository.save(room);
    }

    public void deleteRoomById(Long id) {
<<<<<<< Updated upstream
        Room room = roomRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Room with ID " + id + " not found."));
        roomRepository.deleteById(id);
    }

    public Room updateRoom(Long id, Room updatedRoom) {
=======
//        logger.debug("Deleting room with ID: {}", id);
        Room room = roomRepository.findById(id)
                .orElseThrow(() -> {
                    String errorMessage = "Room with ID " + id + " not found.";
//                    logger.error(errorMessage);
                    return new NotFoundException(errorMessage);
                });
        roomRepository.deleteById(id);
//        logger.info("Room with ID {} deleted successfully", id);
    }

    public Room updateRoom(Long id, Room updatedRoom) {
//        logger.debug("Updating room with ID: {}", id);
>>>>>>> Stashed changes
        return roomRepository.findById(id)
                .map(room -> {
                    room.setRoomCapacity(updatedRoom.getRoomCapacity());
                    room.setEmpty(updatedRoom.isEmpty());
<<<<<<< Updated upstream
                    return roomRepository.save(room);
                })
                .orElseThrow(() -> new NotFoundException("Room not found with id: " + id));
    }

    public List<Room> getEmptyRooms() { //available
        return roomRepository.findByIsEmptyTrue();
    }
}
=======
//                    logger.info("Room with ID {} updated successfully", id);
                    return roomRepository.save(room);
                })
                .orElseThrow(() -> {
                    String errorMessage = "Room not found with id: " + id;
//                    logger.error(errorMessage);
                    return new NotFoundException(errorMessage);
                });
    }

    public List<Room> getEmptyRooms() { //available
//        logger.debug("Fetching empty rooms");
        return roomRepository.findByIsEmptyTrue();
    }

    public boolean isRoomEmpty(long roomId) {
//        logger.info("Checking if room with ID: {} is empty", roomId);
        Room room = roomRepository.findById(roomId)
                .orElseThrow(() -> {
//                    logger.error("Room with ID: {} not found!", roomId);
                    return new NotFoundException("Room with ID: " + roomId + " not found!");
                });
        return room.isEmpty();
    }

    public void updateRoomStatus(long roomId) {
//        logger.info("Updating room status for room with ID: {}", roomId);
        Room room = roomRepository.findById(roomId).map(
                        room1 -> {
                            room1.setEmpty(false);
                            return roomRepository.save(room1);
                        }
                )
                .orElseThrow(() -> {
//                    logger.error("Room with ID: {} not found!", roomId);
                    return new NotFoundException("Room with ID: " + roomId + " not found!");
                });
//        logger.info("Room status for room with ID: {} updated successfully", roomId);
    }

    public Room findRoomById(Long roomId) {
        return roomRepository.findById(roomId)
                .orElseThrow(() -> new NotFoundException("User not found with Id: " + roomId));
    }
}
>>>>>>> Stashed changes
