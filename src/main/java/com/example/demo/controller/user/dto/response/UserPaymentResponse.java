package com.example.demo.controller.user.dto.response;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
public class UserPaymentResponse {
    private Long userId;
    private BigDecimal amount;
}
