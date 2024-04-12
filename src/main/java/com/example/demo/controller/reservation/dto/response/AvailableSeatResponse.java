package com.example.demo.controller.reservation.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class AvailableSeatResponse {

    private Long seatId;
    private int seatNumber;
    private Boolean isReserved;
}
