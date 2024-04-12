package com.example.demo.domain.user.service;

import com.example.demo.controller.user.dto.response.UserBalanceResponse;
import com.example.demo.domain.user.entity.User;
import com.example.demo.domain.user.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @InjectMocks
    UserService userService;

    @Mock
    UserRepository userRepository;

    @Test
    public void 잔액조회_성공(){
        //given
        Long userId=1L;
        when(userRepository.findByUserId(userId)).thenReturn(Optional.of(new User(1L, BigDecimal.valueOf(1000))));

        //when
        UserBalanceResponse response = userService.getBalance(userId);

        //then
        assertThat(response.getUserId()).isEqualTo(userId);
        assertThat(response.getAmount()).isEqualTo(BigDecimal.valueOf(1000));
    }

    @Test
    public void 잔액조회_실패(){
        //given
        Long userId=1L;

        //when & then
        assertThrows(RuntimeException.class, ()->userService.getBalance(userId));
    }

    @Test
    public void 충전_성공(){
        //given
        Long userId=1L;
        when(userRepository.findByUserId(userId)).thenReturn(Optional.of(new User(1L, BigDecimal.valueOf(1000))));

        //when
        UserBalanceResponse response = userService.chargeBalance(userId,BigDecimal.valueOf(1000));

        //then
        assertThat(response.getUserId()).isEqualTo(userId);
        assertThat(response.getAmount()).isEqualTo(BigDecimal.valueOf(2000));
    }

    @Test
    public void 충전금액0원이하인경우_실패(){
        //given
        Long userId=1L;

        //when & then
        assertThrows(RuntimeException.class, ()->userService.chargeBalance(userId,BigDecimal.valueOf(-1000)));
    }

    @Test
    public void 충전_실패(){
        //given
        Long userId=1L;

        //when & then
        assertThrows(RuntimeException.class, ()->userService.chargeBalance(userId,BigDecimal.valueOf(-1000)));
    }

    @Test
    public void 유저조회_성공(){
        //given
        Long userId=1L;
        when(userRepository.findByUserId(userId)).thenReturn(Optional.of(new User(1L, BigDecimal.valueOf(1000))));

        //when
        User response = userService.findUser(userId);

        //then
        assertThat(response.getUserId()).isEqualTo(userId);
    }

    @Test
    public void 유저조회_실패(){
        //given
        Long userId=1L;

        //when & then
        assertThrows(RuntimeException.class, ()->userService.findUser(userId));
    }

}