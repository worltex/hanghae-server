package com.example.demo.domain.concert.repository;

import com.example.demo.domain.concert.entity.Hall;
import com.example.demo.domain.concert.entity.Seat;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SeatRepository extends JpaRepository<Seat, Long> {

     Optional<Seat> findBySeatId(Long seatId);
}
