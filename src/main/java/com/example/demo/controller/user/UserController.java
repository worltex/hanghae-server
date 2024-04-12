package com.example.demo.controller.user;

import com.example.demo.controller.user.dto.request.UserBalanceUpdateRequest;
import com.example.demo.controller.user.dto.response.UserBalanceResponse;
import com.example.demo.controller.user.dto.response.UserPaymentResponse;
import com.example.demo.domain.payment.service.PaymentService;
import com.example.demo.domain.user.service.UserService;
import com.example.demo.domain.waiting.service.WaitingQueueService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final WaitingQueueService waitingQueueService;
    private final UserService userService;
    private final PaymentService paymentService;
    @PostMapping("/{userId}/token")
    public String createToken(@PathVariable Long userId){
        return "token";
    }

    @GetMapping("/{userId}/balance")
    public UserBalanceResponse getBalance(@PathVariable Long userId){
        return userService.getBalance(userId);
    }

    @PostMapping("/{userId}/balance")
    public UserBalanceResponse chargeBalance(@PathVariable Long userId, UserBalanceUpdateRequest request){
        return userService.chargeBalance(userId, request.getAmount());
    }

    @PostMapping("/{userId}/payments/{paymentId}")
    public UserPaymentResponse pay(@PathVariable Long userId, @PathVariable Long paymentId){
        return paymentService.pay(userId, paymentId);
    }
}
