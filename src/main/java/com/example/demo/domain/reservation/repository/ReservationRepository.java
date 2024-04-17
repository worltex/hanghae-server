package com.example.demo.domain.reservation.repository;

import com.example.demo.domain.reservation.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ReservationRepository extends JpaRepository<Reservation,Long> {
    List<Reservation> findAllByShowIdAndStatusIn(Long showId, List<String> status);

    Optional<Reservation> findByShowIdAndSeatId(Long showId, Long seatId);
}
