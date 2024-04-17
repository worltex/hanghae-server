package com.example.demo.domain.concert.repository;

import com.example.demo.domain.concert.entity.Seat;
import com.example.demo.domain.concert.entity.Show;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ShowRepository extends JpaRepository<Show, Long> {

     Optional<Show> findByShowId(Long showId);
}
