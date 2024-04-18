package com.example.demo.domain.reservation.service;

import com.example.demo.controller.reservation.dto.response.ReserveResponse;
import com.example.demo.domain.payment.service.PaymentService;
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
    private final PaymentService paymentService;

    public Set<Long> getAllReveredSeats(Long showId) {
        List<String> status = Arrays.asList("RESERVED", "RESERVING");
        List<Reservation> reservationList = reservationRepository.findAllByShowIdAndStatusIn(showId,status);
        return reservationList.stream().map(Reservation::getSeatId).collect(Collectors.toSet());
    }

    @Transactional
    public ReserveResponse reserveSeat(Long concertId, Long showId, Long seatId, Long userId) {
        reservationValidator.isReserved(showId,seatId);
        Reservation reservation = null;
        try{
            reservation= Reservation.makeReservation(concertId, showId, seatId,userId);
            reservationRepository.save(reservation);
            paymentService.verifyPaymentAsync(reservation.getReservationId());
           return new ReserveResponse(reservation.getReservationId(),  reservation.getStatus());
        }catch (OptimisticEntityLockException e){
            throw new TicketingException(TicketingErrorCode.ALREADY_RESERVED);
        }catch (TicketingException e){
            reservation.updateStatus("FAILED");
            throw new TicketingException(TicketingErrorCode.ALREADY_RESERVED);
        }
    }
}
