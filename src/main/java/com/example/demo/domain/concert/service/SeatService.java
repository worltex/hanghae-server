package com.example.demo.domain.concert.service;

import com.example.demo.domain.concert.entity.Seat;
import com.example.demo.domain.concert.repository.SeatRepository;
import com.example.demo.exception.TicketingErrorCode;
import com.example.demo.exception.TicketingException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class SeatService {

    private final SeatRepository seatRepository;

    @Transactional(readOnly = true)
    public Seat findBySeatId(Long seatId) {
        return seatRepository.findBySeatId(seatId).orElseThrow(()->  new TicketingException(TicketingErrorCode.USER_NOT_FOUND));
    }
}
