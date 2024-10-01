package com.example.meetingroomreservationapi.service;

import com.example.meetingroomreservationapi.dto.ReservationDTO;
import com.example.meetingroomreservationapi.entity.Reservation;
import com.example.meetingroomreservationapi.entity.Room;
import com.example.meetingroomreservationapi.entity.User;

import java.util.List;
import java.util.Optional;

public interface ReservationFacade {
    // Reservation-related methods
    List<ReservationDTO> getAllReservations();
    List<Room> getAvailableRooms();
    Reservation createReservation(ReservationDTO reservationDTO);
    Optional<Reservation> updateReservationStatus(long reservationId, String updatedStatus);
    List<Reservation> getWaitingList();

    // Room-related methods
    List<Room> getAllRooms();
    Room createRoom(Room room);
    void deleteRoomById(Long roomId);
    Room updateRoom(Long roomId, Room updatedRoom);

    // User-related methods
    List<User> getAllUsers();
    User findUserById(Long userId);
    User createUser(User user);
    void deleteUserById(Long userId);
    User updateUser(Long userId, User updatedUser);
}
