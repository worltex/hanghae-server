package com.example.demo.domain.payment.service;

import com.example.demo.controller.user.dto.response.UserPaymentResponse;
import com.example.demo.domain.payment.entity.Payment;
import com.example.demo.domain.payment.repository.PaymentRepository;
import com.example.demo.domain.payment.validator.PaymentValidator;
import com.example.demo.domain.user.entity.User;
import com.example.demo.domain.user.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doCallRealMethod;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PaymentServiceTest {

    @InjectMocks
    PaymentService paymentService;

    @Mock
    PaymentRepository paymentRepository;

    @Mock
    PaymentValidator paymentValidator;
    @Mock
    UserService userService;

    @Test
    public void 결제성공(){
        //given
        Long paymentId=1L;
        Payment payment = new Payment(paymentId,1L,new BigDecimal(1000), null);
        when(paymentRepository.findByPaymentId(paymentId)).thenReturn(Optional.of(payment));
        when(userService.findByUserId(any())).thenReturn(new User(1L,new BigDecimal(2000)));
        doCallRealMethod().when(paymentValidator).checkBalance(any(),any());

        //when
        UserPaymentResponse response = paymentService.pay(1L, 1L);

        //then
        assertThat(response.getAmount()).isEqualTo(new BigDecimal(1000));
    }


    @Test
    public void 결제상태_READY가아닌경우_실패(){
        //given
        Long paymentId=1L;
        Payment payment = new Payment(paymentId,1L,new BigDecimal(1000), null);
        when(paymentRepository.findByPaymentId(paymentId)).thenReturn(Optional.of(payment));

        //when & then
        assertThrows(RuntimeException.class, ()->paymentService.pay(1L, 1L));
    }

    @Test
    public void 유저를찾지못한경우_실패(){
        //given
        Long paymentId=1L;
        Payment payment = new Payment(paymentId,1L,new BigDecimal(1000), null);
        when(paymentRepository.findByPaymentId(paymentId)).thenReturn(Optional.of(payment));

        //when & then
        assertThrows(RuntimeException.class, ()->paymentService.pay(1L, 1L));
    }

    @Test
    public void 잔고가부족한경우_실패(){
        //given
        Long paymentId=1L;
        Payment payment = new Payment(paymentId,1L,new BigDecimal(1000), null);
        when(paymentRepository.findByPaymentId(paymentId)).thenReturn(Optional.of(payment));
        when(userService.findByUserId(any())).thenReturn(new User(1L,new BigDecimal(500)));
        doCallRealMethod().when(paymentValidator).checkBalance(any(),any());

        //when & then
        assertThrows(RuntimeException.class, ()->paymentService.pay(1L, 1L));
    }
}