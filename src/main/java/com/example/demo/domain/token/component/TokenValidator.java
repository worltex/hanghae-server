package com.example.demo.domain.token.component;

import com.example.demo.domain.token.entity.Token;
import com.example.demo.domain.token.repository.TokenRepository;
import com.example.demo.exception.TicketingErrorCode;
import com.example.demo.exception.TicketingException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.ZonedDateTime;
import java.util.List;

@Component
@RequiredArgsConstructor
public class TokenValidator {

    private final TokenRepository tokenRepository;

    @Transactional
    public void isValid(String token) {
        Token tokenEntity = tokenRepository.findByToken(token).orElseThrow(() -> new TicketingException(TicketingErrorCode.USER_NOT_FOUND));

        if(tokenEntity.getExpiredAt()==null ||tokenEntity.getExpiredAt().isAfter(ZonedDateTime.now())){
            throw new TicketingException(TicketingErrorCode.USER_NOT_FOUND);
        }
    }
}
