package com.example.demo.domain.concert.service;

import com.example.demo.controller.reservation.dto.response.AvailableSeatResponse;
import com.example.demo.domain.concert.entity.Concert;
import com.example.demo.domain.concert.entity.Seat;
import com.example.demo.domain.concert.entity.Show;
import com.example.demo.domain.concert.repository.ConcertRepository;
import com.example.demo.domain.concert.validator.ShowValidator;
import com.example.demo.domain.reservation.service.ReservationService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ConcertServiceTest {

    @InjectMocks
    ConcertService concertService;
    @Mock
    ConcertReaderService concertReaderService;
    @Mock
    ShowValidator showValidator;
    @Mock
    ConcertRepository concertRepository;
    @Mock
    ReservationService reservationService;
    @Mock
    HallService hallService;


    @Test
    public void 콘서트_예약가능한날짜_조회_성공(){
        //given
        Long concertId=1L;
        Long showId=1L;
        Show show = new Show(showId, concertId, ZonedDateTime.now());
        Concert test = new Concert(concertId, "test", 1L, Arrays.asList(show));
        doCallRealMethod().when(showValidator).validShow(any());
        when(concertReaderService.findByConcertId(concertId)).thenReturn(test);

        //when
        List<Show> showDates = concertService.getShowDates(concertId);

        //then
        assertThat(showDates.size()).isEqualTo(1);
    }

    @Test
    public void 콘서트_예약가능한날짜_조회_실패(){
        //given
        Long concertId=1L;
        Concert test = new Concert(concertId, "test", 1L, Collections.EMPTY_LIST);
        doCallRealMethod().when(showValidator).validShow(any());
        when(concertReaderService.findByConcertId(concertId)).thenReturn(test);

        //when & then
        assertThrows(RuntimeException.class,()->concertService.getShowDates(concertId));
    }

    @Test
    public void 콘서트_예약가능한좌석_조회_성공(){
        //given
        Long concertId=1L;
        Long showId=1L;
        Long hallId=1L;
        Show show = new Show(showId, concertId, ZonedDateTime.now());
        when(concertReaderService.findByConcertId(concertId)).thenReturn(new Concert(concertId, "test", 1L, Arrays.asList(show)));
        when(hallService.getAllSeats(hallId)).thenReturn(Arrays.asList(new Seat(1L,1, new BigDecimal(1000))));
        when(reservationService.getAllReveredSeats(showId)).thenReturn(Set.of(1L));
        //when
        List<AvailableSeatResponse> availableSeats = concertService.getAvailableSeats(concertId, showId);

        //then
        assertThat(availableSeats.size()).isEqualTo(1);
        assertThat(availableSeats.get(0).getIsReserved()).isEqualTo(true);
    }
}