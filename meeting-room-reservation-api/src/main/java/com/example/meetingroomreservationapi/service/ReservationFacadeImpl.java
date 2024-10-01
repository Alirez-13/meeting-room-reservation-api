package com.example.meetingroomreservationapi.service;

import com.example.meetingroomreservationapi.dto.ReservationDTO;
import com.example.meetingroomreservationapi.entity.Reservation;
import com.example.meetingroomreservationapi.entity.Room;
import com.example.meetingroomreservationapi.entity.User;
import com.example.meetingroomreservationapi.excHandler.RoomOccupiedException;
import com.example.meetingroomreservationapi.service.ReservationService;
import com.example.meetingroomreservationapi.service.RoomService;
import com.example.meetingroomreservationapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class ReservationFacadeImpl implements ReservationFacade {

    @Autowired
    private ReservationService reservationService;

    @Autowired
    private RoomService roomService;

    @Autowired
    private UserService userService;

    // Reservation-related methods
    @Override
    public List<ReservationDTO> getAllReservations() {
        return reservationService.getAllReservation();
    }

    @Override
    public List<Room> getAvailableRooms() {
        return reservationService.getAvailableRoom();
    }

    @Override
    public Reservation createReservation(ReservationDTO reservationDTO) {
        return reservationService.CreateReservation(reservationDTO);
    }

    @Override
    public Optional<Reservation> updateReservationStatus(long reservationId, String updatedStatus) {
        return reservationService.updateReservationStatus(reservationId, updatedStatus);
    }

    @Override
    public List<Reservation> getWaitingList() {
        return reservationService.waitingList();
    }

    // Room-related methods
    @Override
    public List<Room> getAllRooms() {
        return roomService.getAllRooms();
    }

    @Override
    public Room createRoom(Room room) {
        return roomService.createRoom(room);
    }

    @Override
    public void deleteRoomById(Long roomId) {
        roomService.deleteRoomById(roomId);
    }

    @Override
    public Room updateRoom(Long roomId, Room updatedRoom) {
        return roomService.updateRoom(roomId, updatedRoom);
    }

    // User-related methods
    @Override
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @Override
    public User findUserById(Long userId) {
        return userService.findUserById(userId);
    }

    @Override
    public User createUser(User user) {
        userService.saveUser(user);
        return user;
    }

    @Override
    public void deleteUserById(Long userId) {
        userService.deleteUserById(userId);
    }

    @Override
    public User updateUser(Long userId, User updatedUser) {
        return userService.updateUser(updatedUser, userId);
    }
}
