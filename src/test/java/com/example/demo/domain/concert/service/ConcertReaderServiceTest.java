package com.example.demo.domain.concert.service;

import com.example.demo.domain.concert.entity.Concert;
import com.example.demo.domain.concert.repository.ConcertRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ConcertReaderServiceTest {

    @InjectMocks
    ConcertReaderService concertReaderService;


    @Mock
    ConcertRepository concertRepository;


    @Test
    public void 콘서트조회_성공(){
        //given
        Long concertId=1L;
        Concert test = new Concert(concertId, "test", 1L, Collections.EMPTY_LIST);
        when(concertRepository.findByConcertId(concertId)).thenReturn(Optional.of(test));

        //when
        Concert concert = concertReaderService.findByConcertId(concertId);

        //then
        assertThat(concert.getConcertId()).isEqualTo(concertId);
    }


    @Test
    public void 콘서트조회_실패(){
        //given
        Long concertId=1L;
        when(concertRepository.findByConcertId(concertId)).thenThrow(new RuntimeException());

        //when & then
        assertThrows(RuntimeException.class,()->concertReaderService.findByConcertId(concertId));
    }
}
