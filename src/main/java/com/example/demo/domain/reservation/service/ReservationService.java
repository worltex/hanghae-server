package com.example.demo.domain.reservation.service;

import com.example.demo.domain.reservation.entity.Reservation;
import com.example.demo.domain.reservation.repository.ReservationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReservationService {

    private final ReservationRepository reservationRepository;

    public Set<Long> getAllReveredSeats(Long showId) {
        List<String> status = Arrays.asList("RESERVED", "RESERVING");
        List<Reservation> reservationList = reservationRepository.findAllByShowIdAndStatusIn(showId,status);
        return reservationList.stream().map(reservation -> reservation.getSeat().getSeatId()).collect(Collectors.toSet());
    }
}
