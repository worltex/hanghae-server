package com.example.demo.domain.reservation.service;

import com.example.demo.domain.concert.entity.Seat;
import com.example.demo.domain.reservation.entity.Reservation;
import com.example.demo.domain.reservation.repository.ReservationRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ReservationServiceTest {

    @InjectMocks
    ReservationService reservationService;
    @Mock
    ReservationRepository reservationRepository;
    @Test
    public void 예약된좌석조회_성공(){
        //given
        Long showId=1L;
        when(reservationRepository.findAllByShowIdAndStatusIn(any(), anyList())).thenReturn(Arrays.asList(new Reservation(1L,1L,1L,1L,1L,"RESERVED", ZonedDateTime.now(), 1L)));

        //when
        Set<Long> reservedSeatSet = reservationService.getAllReveredSeats(showId);

        //then
        assertThat(reservedSeatSet.size()).isEqualTo(1);
    }

}