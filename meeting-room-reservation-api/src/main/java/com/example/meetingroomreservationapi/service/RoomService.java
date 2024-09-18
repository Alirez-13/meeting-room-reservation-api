package com.example.meetingroomreservationapi.service;

import com.example.meetingroomreservationapi.entity.Room;
import com.example.meetingroomreservationapi.errHandler.NotFoundException;
import com.example.meetingroomreservationapi.errHandler.RoomOccupiedException;
import com.example.meetingroomreservationapi.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoomService {

    @Autowired
    private RoomRepository roomRepository;

    public List<Room> getAllRooms() {
        return roomRepository.findAll();
    }

    public Room createRoom(Room room) {
        return roomRepository.save(room);
    }

    public void deleteRoomById(Long id) {
        Room room = roomRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Room with ID " + id + " not found."));
        roomRepository.deleteById(id);
    }

    public Room updateRoom(Long id, Room updatedRoom) {
        return roomRepository.findById(id)
                .map(room -> {
                    room.setRoomCapacity(updatedRoom.getRoomCapacity());
                    room.setEmpty(updatedRoom.isEmpty());
                    return roomRepository.save(room);
                })
                .orElseThrow(() -> new NotFoundException("Room not found with ID: " + id));
    }

    public List<Room> getEmptyRooms() { //available
        return roomRepository.findByIsEmptyTrue();
    }

    public boolean isRoomEmpty(long roomId) {
        Room room = roomRepository.findById(roomId)
                .orElseThrow(() -> new NotFoundException("Room with ID: " + roomId + " not found!"));
        return room.isEmpty();
    }

    public void updateRoomStatus(long roomId) {
        Room room = roomRepository.findById(roomId).map(
                        room1 -> {
                            room1.setEmpty(false);
                            return roomRepository.save(room1);
                        }
                )
                .orElseThrow(() -> new NotFoundException("Room with ID: " + roomId + " not found!"));
    }
}