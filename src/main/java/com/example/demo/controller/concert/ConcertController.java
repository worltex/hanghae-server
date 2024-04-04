package com.example.demo.controller.concert;

import com.example.demo.controller.concert.dto.response.ShowDateResponse;
import com.example.demo.domain.concert.service.ConcertService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.ZonedDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/v1/concerts")
@RequiredArgsConstructor
public class ConcertController {

    private final ConcertService concertService;


    @GetMapping("/{concertId}/shows")
    public List<ShowDateResponse> getAvailableShowDates(@PathVariable String concertId){
        return List.of(new ShowDateResponse("1", ZonedDateTime.now()));
    }

}
