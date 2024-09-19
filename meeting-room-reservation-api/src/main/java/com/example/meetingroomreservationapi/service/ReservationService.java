package com.example.meetingroomreservationapi.service;

import com.example.meetingroomreservationapi.entity.Reservation;
import com.example.meetingroomreservationapi.entity.Room;
import com.example.meetingroomreservationapi.errHandler.NotFoundException;
import com.example.meetingroomreservationapi.errHandler.RoomOccupiedException;
import com.example.meetingroomreservationapi.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ReservationService {

    @Autowired
    ReservationRepository reserveRepository;

    @Autowired
    RoomService roomService;

    public List<Reservation> getAllReservation() {
        return reserveRepository.findAll();
    }

    public List<Room> getAvailableRoom() {
        return roomService.getEmptyRooms();
    }

    public Reservation CreateReservation(Reservation reservation) {
        LocalDate checkIn = reservation.getCheckInDate();
        LocalDate checkOut = reservation.getCheckOutDate();

        // validate Date
        if (checkIn.equals(checkOut) || checkIn.isAfter(checkOut)) {
            throw new IllegalArgumentException("Start date must be before the end date.");
        }

        // validate Room status
        if (!roomService.isRoomEmpty(reservation.getRoomId())) {
            throw new RoomOccupiedException("Room is occupied with ID:" + reservation.getRoomId());
        }
        // id is auto increment
        Reservation requestedReserve = new Reservation(1, reservation.getUserId(), reservation.getRoomId(), checkIn, checkOut, "WAITING");
        return reserveRepository.save(requestedReserve);
    }

    public Optional<Reservation> updateReservationStatus(long reservationId, String updatedStatus) {
        Optional<Reservation> reservation = reserveRepository.findById(reservationId);
        if (reservation.isPresent()) {
            roomService.updateRoomStatus(reservation.get().getRoomId());
            reservation.get().setStatus(updatedStatus);
        }
        return reservation;
    }

    public List<Reservation> waitingList() {
        List<Reservation> waitingReservations = reserveRepository.findByStatus("WAITING");
        if (waitingReservations.isEmpty()) {
            throw new NotFoundException("There are no reservation requests with status 'WAITING'!");
        }
        return waitingReservations;
    }
}
