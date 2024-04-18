package com.example.demo.controller.reservation.dto.response;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ReserveResponse {

    private Long reservationId;

    private String status;
}
