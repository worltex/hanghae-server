package com.example.demo.domain.concert.service;

import com.example.demo.controller.reservation.dto.response.AvailableSeatResponse;
import com.example.demo.domain.concert.entity.Concert;
import com.example.demo.domain.concert.entity.Seat;
import com.example.demo.domain.concert.entity.Show;
import com.example.demo.domain.concert.repository.ConcertRepository;
import com.example.demo.domain.concert.validator.ShowValidator;
import com.example.demo.domain.reservation.service.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class ConcertService {
    private final ShowValidator showValidator;
    private final ReservationService reservationService;
    private final HallService hallService;
    private final ConcertRepository concertRepository;

    @Transactional(readOnly = true)
    public List<Show> getShowDates(Long concertId) {
        List<Show> showDateList= getConcert(concertId).getShowDateList();
        showValidator.validShow(showDateList);
        return showDateList;
    }

    @Transactional(readOnly = true)
    public List<AvailableSeatResponse> getAvailableSeats(Long concertId, Long showId) {
        Long hallId  = getConcert(concertId).getHallId();
        List<Seat> allSeats = hallService.getAllSeats(hallId);

        Set<Long> reveredSeats = reservationService.getAllReveredSeats(showId);

        return allSeats.stream().map(seat-> new AvailableSeatResponse(seat.getSeatId(), seat.getSeatNum(), reveredSeats.contains(seat.getSeatId()))).toList();
    }

    public Concert getConcert(Long concertId) {
        return concertRepository.findByConcertId(concertId)
                .orElseThrow(()->new RuntimeException("콘서트 정보를 조회할 수 없습니다."));
    }
}
