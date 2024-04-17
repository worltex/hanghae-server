package com.example.demo.domain.reservation.validator;

import com.example.demo.domain.reservation.entity.Reservation;
import com.example.demo.domain.reservation.repository.ReservationRepository;
import com.example.demo.exception.TicketingErrorCode;
import com.example.demo.exception.TicketingException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ReservationValidator {


    private final ReservationRepository reservationRepository;

    private final List<String> UNAVAILABLE_STATES= List.of("RESERVED", "RESERVING");

    @Transactional(readOnly = true)
    public void isReserved(Long showId, Long seatId){
        reservationRepository.findByShow_ShowIdAndSeat_SeatId(showId, seatId)
                .filter(reservation -> UNAVAILABLE_STATES.contains(reservation.getStatus()))
                .ifPresent(reservation->{
                    throw new TicketingException(TicketingErrorCode.ALREADY_RESERVED);
                });
    }

}
