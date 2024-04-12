package com.example.demo.domain.concert.repository;

import com.example.demo.domain.concert.entity.Concert;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ConcertRepository extends JpaRepository<Concert, Long> {

    Optional<Concert> findByConcertId(Long concertId);
}
