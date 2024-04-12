package com.example.demo.domain.user.repository;

import com.example.demo.domain.payment.entity.Payment;
import com.example.demo.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PaymentRepository extends JpaRepository<Payment,Long> {
    Optional<Payment> findByPaymentId(Long paymentId);
}
