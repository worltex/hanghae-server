package com.example.demo.controller.user.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class UserBalanceUpdateRequest {

    @Schema(name = "amount")
    private BigDecimal amount;
}
