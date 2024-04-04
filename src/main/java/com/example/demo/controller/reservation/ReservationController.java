package com.example.demo.controller.reservation;

import com.example.demo.controller.reservation.dto.response.AvailableSeatResponse;
import com.example.demo.domain.reservation.service.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/shows")
@RequiredArgsConstructor
public class ReservationController {

    private final ReservationService reservationService;

    @GetMapping("/showId}/seats")
    public List<AvailableSeatResponse> getAvailableSeats(@PathVariable String showId){
        return List.of(new AvailableSeatResponse("1","1"));
    }
}
