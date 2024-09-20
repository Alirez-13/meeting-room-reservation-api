package com.example.meetingroomreservationapi.service;

import com.example.meetingroomreservationapi.entity.Reservation;
import com.example.meetingroomreservationapi.entity.Room;
import com.example.meetingroomreservationapi.excHandler.NotFoundException;
import com.example.meetingroomreservationapi.excHandler.RoomOccupiedException;
import com.example.meetingroomreservationapi.repository.ReservationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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


    private static final Logger logger = LoggerFactory.getLogger(ReservationService.class);
    public List<Reservation> getAllReservation() {
        logger.info("Fetching all reservations.");
        return reserveRepository.findAll();
    }

    public List<Room> getAvailableRoom() {
        logger.info("Fetching all available rooms.");
        return roomService.getEmptyRooms();
    }

    public Reservation CreateReservation(Reservation reservation) {
        logger.info("Creating a reservation for room ID: {} by user ID: {}",
                reservation.getRoomId(), reservation.getUserId());
        LocalDate checkIn = reservation.getCheckInDate();
        LocalDate checkOut = reservation.getCheckOutDate();

        // validate Date
        if (checkIn.equals(checkOut) || checkIn.isAfter(checkOut)) {
            logger.error("Invalid reservation dates: Check-in date is {}, Check-out date is {}",
                    checkIn, checkOut);
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
        logger.info("Updating status of reservation with ID: {} to '{}'", reservationId, updatedStatus);
        Optional<Reservation> reservation = reserveRepository.findById(reservationId);
        if (reservation.isPresent()) {
            roomService.updateRoomStatus(reservation.get().getRoomId());
            reservation.get().setStatus(updatedStatus);
            logger.info("Reservation status updated successfully for ID: {}", reservationId);
        }
        return reservation;
    }

    public List<Reservation> waitingList() {
        logger.info("Fetching all reservations with status 'WAITING'.");
        List<Reservation> waitingReservations = reserveRepository.findByStatus("WAITING");
        if (waitingReservations.isEmpty()) {
            throw new NotFoundException("There are no reservation requests with status 'WAITING'!");
        }
        logger.info("Found {} reservations with status 'WAITING'.", waitingReservations.size());
        return waitingReservations;
    }
}