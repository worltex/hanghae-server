package com.example.demo.domain.payment.service;

import com.example.demo.controller.user.dto.response.UserPaymentResponse;
import com.example.demo.domain.payment.entity.Payment;
import com.example.demo.domain.user.entity.User;
import com.example.demo.domain.payment.repository.PaymentRepository;
import com.example.demo.domain.payment.validator.PaymentValidator;
import com.example.demo.domain.user.service.UserService;
import com.example.demo.exception.TicketingErrorCode;
import com.example.demo.exception.TicketingException;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final PaymentValidator paymentValidator;
    private final PaymentRepository paymentRepository;
    private final UserService userService;

    @Transactional
    public UserPaymentResponse pay(Long userId, Long paymentId) {
        Payment payment = paymentRepository.findByPaymentId(paymentId)
                .orElseThrow(() -> new RuntimeException("결제를 진행할 수 없습니다."));
        BigDecimal price = payment.getPaymentPrice();

        User user = userService.findByUserId(userId);
        paymentValidator.checkBalance(price, user.getBalance());

        BigDecimal usedBalance = calculateBalance(payment);
        user.updateBalance(usedBalance);

        return new UserPaymentResponse(userId, user.getBalance());

    }

    private BigDecimal calculateBalance(Payment payment) {
        return payment.getPaymentPrice().multiply(new BigDecimal(-1));
    }

    @Async
    @Transactional
    public CompletableFuture<Void> verifyPaymentAsync(Long reservationId) {
        try {
            // 5분 대기
            TimeUnit.MINUTES.sleep(5);
            paymentRepository.findByReservationId(reservationId).orElseThrow(() -> new TicketingException(TicketingErrorCode.ALREADY_RESERVED));
            return CompletableFuture.completedFuture(null);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return CompletableFuture.completedFuture(null);
        }
    }
}
