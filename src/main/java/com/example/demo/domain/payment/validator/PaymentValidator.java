package com.example.demo.domain.payment.validator;

import com.example.demo.domain.payment.entity.Payment;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class PaymentValidator {
    public void checkBalance(BigDecimal paymentPrice, BigDecimal balance) {
        if(balance.compareTo(paymentPrice)<0){
            throw new RuntimeException("잔액이 부족합니다.");
        }
    }
}
