package com.example.demo.domain.payment.repository;

import com.example.demo.domain.payment.entity.Payment;
import com.example.demo.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PaymentRepository extends JpaRepository<Payment,Long> {
    Optional<Payment> findByPaymentId(Long paymentId);

    Optional<Payment> findByReservationId(Long reservationId);
}
