package com.example.demo.domain.concert.repository;

import com.example.demo.domain.concert.entity.Concert;
import com.example.demo.domain.concert.entity.Hall;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface HallRepository extends JpaRepository<Hall, Long> {

}
