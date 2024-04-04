package com.example.demo.controller.reservation.dto.response;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;

@NoArgsConstructor
@AllArgsConstructor
public class AvailableSeatResponse {

    private String seatId;
    private String seatNumber;
}
