package com.example.demo.controller.user.dto.request;

import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class UserBalanceUpdateRequest {

    private BigDecimal amount;
}
