package com.example.demo.controller.reservation;

import com.example.demo.controller.reservation.dto.response.AvailableSeatResponse;
import com.example.demo.domain.reservation.service.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/shows")
@RequiredArgsConstructor
public class ReservationController {

    private final ReservationService reservationService;

    @PostMapping("/{showId}/seats/{seatId}")
    public ResponseEntity<Void> reserveSeat(@PathVariable String seatId){
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
