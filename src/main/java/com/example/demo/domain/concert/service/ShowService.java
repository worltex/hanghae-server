package com.example.demo.domain.concert.service;

import com.example.demo.domain.concert.entity.Show;
import com.example.demo.domain.concert.repository.ShowRepository;
import com.example.demo.exception.TicketingErrorCode;
import com.example.demo.exception.TicketingException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ShowService {

    private final ShowRepository showRepository;


    public Show findByShowId(Long showId){
        return showRepository.findByShowId(showId).orElseThrow(()->new TicketingException(TicketingErrorCode.USER_NOT_FOUND));
    }
}
