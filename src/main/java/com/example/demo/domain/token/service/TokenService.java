package com.example.demo.domain.token.service;

import com.example.demo.domain.token.entity.Token;
import com.example.demo.domain.token.repository.TokenRepository;
import com.example.demo.domain.user.entity.User;
import com.example.demo.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.ZonedDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TokenService {

    private final UserService userService;

    private final TokenRepository tokenRepository;


    @Transactional
    public String createToken(Long userId) {
        User user = userService.findByUserId(userId);

        Long number = tokenRepository.selectNextWaitNumber();

        String token = UUID.nameUUIDFromBytes(String.valueOf(userId).getBytes()).toString();
        Long activeUser = tokenRepository.getProgressStatusCount("ACTIVE");
        String status = activeUser<50?"ACTIVE":"WAIT";

        Token tokenEntity = tokenRepository.save(Token.builder()
                .user(user)
                .token(token)
                .waitNo(number)
                .status(status)
                .createdAt(ZonedDateTime.now())
                .expiredAt(ZonedDateTime.now().plusMinutes(10))
                .build());

        return tokenEntity.getToken();
    }
}
