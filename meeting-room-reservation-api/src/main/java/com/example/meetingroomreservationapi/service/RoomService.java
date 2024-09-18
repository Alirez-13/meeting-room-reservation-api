package com.example.meetingroomreservationapi.service;

import com.example.meetingroomreservationapi.entity.Room;
import com.example.meetingroomreservationapi.errHandler.NotFoundException;
import com.example.meetingroomreservationapi.repository.RoomRepository;
import com.example.meetingroomreservationapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
                .orElseThrow(() -> new NotFoundException("Room not found with id: " + id));
    }

    public List<Room> getEmptyRooms() { //available
        return roomRepository.findByIsEmptyTrue();
    }
}
