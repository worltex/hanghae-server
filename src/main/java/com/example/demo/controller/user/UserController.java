package com.example.demo.controller.user;

import com.example.demo.controller.user.dto.response.UserBalanceResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    @PostMapping("/{userId}/token")
    public String createToken(@PathVariable String userId){
        return "token";
    }

    @GetMapping("/{userId}/balance")
    public UserBalanceResponse getBalance(@PathVariable String userId){
        return new UserBalanceResponse(0L);
    }

    @PostMapping("/{userId}/balance")
    public UserBalanceResponse chargeBalance(@PathVariable String userId){
        return new UserBalanceResponse(0L);
    }

    @PostMapping("/{userId}/payments")
    public UserBalanceResponse pay(@PathVariable String userId){
        return new UserBalanceResponse(0L);
    }
}
