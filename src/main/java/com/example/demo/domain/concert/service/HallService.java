package com.example.demo.domain.concert.service;

import com.example.demo.domain.concert.entity.Seat;
import com.example.demo.domain.concert.repository.HallRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HallService {

    private final HallRepository hallRepository;

    List<Seat> getAllSeats(Long hallId){
       return hallRepository.findById(hallId).orElseThrow(()->new RuntimeException("콘서트 정보를 조회할 수 없습니다.")).getSeatList();
    }
}
