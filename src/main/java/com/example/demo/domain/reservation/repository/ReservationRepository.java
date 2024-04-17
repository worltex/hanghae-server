package com.example.demo.domain.reservation.repository;

import com.example.demo.domain.reservation.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ReservationRepository extends JpaRepository<Reservation,Long> {
    List<Reservation> findAllByShow_ShowIdAndStatusIn(Long showId, List<String> status);

    Optional<Reservation> findByShow_ShowIdAndSeat_SeatId(Long showId, Long seatId);
}
