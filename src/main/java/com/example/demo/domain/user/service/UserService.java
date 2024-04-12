package com.example.demo.domain.user.service;

import com.example.demo.controller.user.dto.response.UserBalanceResponse;
import com.example.demo.domain.user.entity.User;
import com.example.demo.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    @Transactional(readOnly = true)
    public UserBalanceResponse getBalance(Long userId) {
        User user = userRepository.findByUserId(userId).orElseThrow(() -> new RuntimeException("사용자를 찾을 수 없습니다."));
        return new UserBalanceResponse(user.getUserId(),user.getBalance());
    }

    @Transactional
    public UserBalanceResponse chargeBalance(Long userId, BigDecimal amount) {
        if(amount.compareTo(BigDecimal.valueOf(0))<0){
            throw new RuntimeException("1원 이상 충전 가능합니다.");
        }
        User user = userRepository.findByUserId(userId).orElseThrow(() -> new RuntimeException("사용자를 찾을 수 없습니다."));
        user.updateBalance(amount);
        return new UserBalanceResponse(user.getUserId(),user.getBalance());
    }

    @Transactional(readOnly = true)
    public User findUser(Long userId) {
        return userRepository.findByUserId(userId).orElseThrow(() -> new RuntimeException("사용자를 찾을 수 없습니다."));
    }
}
