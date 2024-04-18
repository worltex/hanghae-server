package com.example.demo.controller.reservation.dto.request;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;

@Getter
public class ReserveRequest {

    @NotEmpty
    private Long concertId;
    @NotEmpty
    private Long userId;
}
