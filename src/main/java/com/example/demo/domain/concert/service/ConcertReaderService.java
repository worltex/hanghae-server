package com.example.demo.domain.concert.service;

import com.example.demo.domain.concert.entity.Concert;
import com.example.demo.domain.concert.repository.ConcertRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ConcertReaderService {

    private final ConcertRepository concertRepository;


    @Transactional(readOnly = true)
    public Concert findByConcertId(Long concertId) {
        return concertRepository.findByConcertId(concertId)
                .orElseThrow(()->new RuntimeException("콘서트 정보를 조회할 수 없습니다."));
    }
}
