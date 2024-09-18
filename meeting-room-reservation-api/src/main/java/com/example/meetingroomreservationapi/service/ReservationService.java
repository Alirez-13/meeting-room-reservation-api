package com.example.meetingroomreservationapi.service;

import com.example.meetingroomreservationapi.entity.Reservation;
import com.example.meetingroomreservationapi.entity.Room;
import com.example.meetingroomreservationapi.errHandler.RoomOccupiedException;
import com.example.meetingroomreservationapi.repository.ReservationRepository;
import jdk.vm.ci.meta.Local;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

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

    public Reservation reservationReq(Reservation reservation) {
        LocalDate checkIn = reservation.getCheckInDate();
        LocalDate checkOut = reservation.getCheckOutDate();

        // validate Date
        if (checkIn.equals(checkOut) || checkIn.isAfter(checkOut)){
            throw new IllegalArgumentException("Start date must be before the end date.");
        }

        // validate Room status
        if (!roomService.isRoomEmpty(reservation.getRoomId())) {
            throw new RoomOccupiedException("Room is occupied with ID:" + reservation.getRoomId());
        }
        return reserveRepository.save(reservation);
    }
}
