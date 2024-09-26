package com.example.meetingroomreservationapi.service;

import com.example.meetingroomreservationapi.dto.ReservationDTO;
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
import java.util.stream.Collectors;

@Service
public class ReservationService {

    @Autowired
    ReservationRepository reserveRepository;

    @Autowired
    RoomService roomService;

    @Autowired
    UserService userService;

    private static final Logger logger = LoggerFactory.getLogger(ReservationService.class);

    public List<ReservationDTO> getAllReservation() {
        logger.info("Fetching all reservations.");
        List<Reservation> reservations = reserveRepository.findAll();
        return reservations.stream()
                .map(this::convToDto) // Convert each Reservation entity to ReservationDTO
                .collect(Collectors.toList()); // Collect the results as a List
    }

    public List<Room> getAvailableRoom() {
        logger.info("Fetching all available rooms.");
        return roomService.getEmptyRooms();
    }

    public Reservation CreateReservation(ReservationDTO reservationDTO) {

        LocalDate checkIn = reservationDTO.getCheckInDate();
        LocalDate checkOut = reservationDTO.getCheckOutDate();

        // validate Date
        if (checkIn.equals(checkOut) || checkIn.isAfter(checkOut)) {
            logger.error("Invalid reservation dates: Check-in date is {}, Check-out date is {}",
                    checkIn, checkOut);
            throw new IllegalArgumentException("Start date must be before the end date.");
        }
        // validate Room status
        if (!roomService.isRoomEmpty(reservationDTO.getRoomId())) {
            throw new RoomOccupiedException("Room is occupied with ID:" + reservationDTO.getRoomId());
        }

        return reserveRepository.save(convToEntity(reservationDTO));
    }

    public Optional<Reservation> updateReservationStatus(long reservationId, String updatedStatus) {
        logger.info("Updating status of reservation with ID: {} to '{}'", reservationId, updatedStatus);
        Optional<Reservation> reservation = reserveRepository.findById(reservationId);
        if (reservation.isPresent()) {
            roomService.updateRoomStatus(reservation.get().getRoom().getRoomId());
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

    public ReservationDTO convToDto(Reservation reservation) {
         /*
        return new ReservationDTO(
                reservation.getReservationId(),
                reservation.getUser().getId(),
                reservation.getRoom().getRoomId(),
                reservation.getCheckInDate(),
                reservation.getCheckOutDate(),
                reservation.getStatus()
        );
        */
        ReservationDTO reservationDTO = new ReservationDTO();
        reservationDTO.setReservationId(reservation.getReservationId());
        reservationDTO.setUserId(reservation.getUser().getId());
        reservationDTO.setRoomId(reservation.getRoom().getRoomId());
        reservationDTO.setCheckInDate(reservation.getCheckInDate());
        reservationDTO.setCheckOutDate(reservation.getCheckOutDate());
        reservationDTO.setStatus(reservation.getStatus());
        return reservationDTO;
    }

    public Reservation convToEntity(ReservationDTO reservationDTO) {
        return new Reservation(
        reservationDTO.getReservationId(),
                userService.findUserById(reservationDTO.getUserId()),
                roomService.findRoomById(reservationDTO.getRoomId()),
                reservationDTO.getCheckInDate(),
                reservationDTO.getCheckOutDate(),
                reservationDTO.getStatus()
        );
    }
}