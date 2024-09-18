package com.example.meetingroomreservationapi.repository;

import com.example.meetingroomreservationapi.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {


    List<Reservation> findAll();

    Reservation findByReservationId(Long ReservationId);
    List<Reservation> findByUserId(long userId);

    List<Reservation> findByStatus(String status);
}