package com.example.demo.domain.payment.service;

import com.example.demo.controller.user.dto.response.UserPaymentResponse;
import com.example.demo.domain.payment.entity.Payment;
import com.example.demo.domain.user.entity.User;
import com.example.demo.domain.payment.repository.PaymentRepository;
import com.example.demo.domain.payment.validator.PaymentValidator;
import com.example.demo.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final PaymentValidator paymentValidator;
    private final PaymentRepository paymentRepository;
    private final UserService userService;


    public UserPaymentResponse pay(Long userId, Long paymentId) {
        Payment payment = paymentRepository.findByPaymentId(paymentId)
                .orElseThrow(() -> new RuntimeException("결제를 진행할 수 없습니다."));
        paymentValidator.checkPay(payment);
        BigDecimal price = payment.getPaymentPrice();

        User user = userService.findUser(userId);
        paymentValidator.checkBalance(price, user.getBalance());

        BigDecimal usedBalance = calculateBalance(payment);
        user.updateBalance(usedBalance);

        return new UserPaymentResponse(userId, user.getBalance().subtract(price));

    }

    private BigDecimal calculateBalance(Payment payment) {
        return payment.getPaymentPrice().multiply(new BigDecimal(-1));
    }
}
