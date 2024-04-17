package com.example.demo.domain.reservation.service;

import com.example.demo.domain.reservation.entity.Reservation;
import com.example.demo.domain.reservation.repository.ReservationRepository;
import com.example.demo.domain.reservation.validator.ReservationValidator;
import com.example.demo.exception.TicketingErrorCode;
import com.example.demo.exception.TicketingException;
import lombok.RequiredArgsConstructor;
import org.hibernate.dialect.lock.OptimisticEntityLockException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final ReservationValidator reservationValidator;

    public Set<Long> getAllReveredSeats(Long showId) {
        List<String> status = Arrays.asList("RESERVED", "RESERVING");
        List<Reservation> reservationList = reservationRepository.findAllByShowIdAndStatusIn(showId,status);
        return reservationList.stream().map(Reservation::getSeatId).collect(Collectors.toSet());
    }

    @Transactional
    public void reserveSeat(Long concertId, Long showId, Long seatId, Long userId) {
        reservationValidator.isReserved(showId,seatId);
        try{
            Reservation reservation= Reservation.makeReservation(concertId, showId, seatId,userId);
            reservationRepository.save(reservation);
        }catch (OptimisticEntityLockException e){
            throw new TicketingException(TicketingErrorCode.ALREADY_RESERVED);
        }
    }
}
