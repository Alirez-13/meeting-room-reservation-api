package com.example.meetingroomreservationapi.repository;

import com.example.meetingroomreservationapi.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {


    List<Reservation> findAll();

    Reservation findByReservationId(Long ReservationId);
    List<Reservation> findByUserId(long userId);

}