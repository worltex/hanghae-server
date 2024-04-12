package com.example.demo.domain.concert.service;

import com.example.demo.domain.concert.entity.Hall;
import com.example.demo.domain.concert.entity.Seat;
import com.example.demo.domain.concert.repository.HallRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class HallServiceTest {

    @InjectMocks
    HallService hallService;
    @Mock
    HallRepository hallRepository;

    @Test
    public void 전체좌석조회_성공(){
        //given
        Long hallId = 1L;
        when(hallRepository.findByHallId(hallId)).thenReturn(Optional.of(new Hall(hallId,"testHall", 1, Arrays.asList(new Seat(1L,1,new BigDecimal(1000))))));

        //when
        List<Seat> allSeats = hallService.getAllSeats(hallId);

        //then
        assertThat(allSeats.size()).isEqualTo(1);
    }

    @Test
    public void 전체좌석조회_실패(){
        //given
        Long hallId = 1L;

        //when & then
        assertThrows(RuntimeException.class, ()->hallService.getAllSeats(hallId));
    }
}