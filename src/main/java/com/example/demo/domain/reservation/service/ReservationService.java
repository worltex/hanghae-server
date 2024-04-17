package com.example.demo.domain.reservation.service;

import com.example.demo.domain.concert.entity.Concert;
import com.example.demo.domain.concert.entity.Seat;
import com.example.demo.domain.concert.entity.Show;
import com.example.demo.domain.concert.service.ConcertService;
import com.example.demo.domain.concert.service.SeatService;
import com.example.demo.domain.concert.service.ShowService;
import com.example.demo.domain.reservation.entity.Reservation;
import com.example.demo.domain.reservation.repository.ReservationRepository;
import com.example.demo.domain.reservation.validator.ReservationValidator;
import com.example.demo.domain.user.entity.User;
import com.example.demo.domain.user.service.UserService;
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
    private final UserService userService;
    private final SeatService seatService;
    private final ShowService showService;
    private final ConcertService concertService;

    public Set<Long> getAllReveredSeats(Long showId) {
        List<String> status = Arrays.asList("RESERVED", "RESERVING");
        List<Reservation> reservationList = reservationRepository.findAllByShow_ShowIdAndStatusIn(showId,status);
        return reservationList.stream().map(reservation -> reservation.getSeat().getSeatId()).collect(Collectors.toSet());
    }

    @Transactional
    public void reserveSeat(Long concertId, Long showId, Long seatId, Long userId) {
        reservationValidator.isReserved(showId,seatId);
        Concert concert = concertService.findByConcertId(concertId);
        User user = userService.findByUserId(userId);
        Seat seat = seatService.findBySeatId(seatId);
        Show show = showService.findByShowId(showId);
        try{
            Reservation reservation= Reservation.makeReservation(concert, show, seat,user);
            reservationRepository.save(reservation);
        }catch (OptimisticEntityLockException e){
            throw new TicketingException(TicketingErrorCode.ALREADY_RESERVED);
        }
    }
}
