package com.example.demo.domain.user.service;

import com.example.demo.domain.payment.entity.Payment;
import com.example.demo.domain.user.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Component
public class PaymentValidator {

    public void checkPay(Payment payment) {
        if(!payment.getStatus().equals("READY")){
            throw new RuntimeException("결제를 진행할 수 없습니다.");
        }
    }

    public void checkBalance(BigDecimal paymentPrice, BigDecimal balance) {
        if(balance.compareTo(paymentPrice)<0){
            throw new RuntimeException("잔액이 부족합니다.");
        }
    }
}
