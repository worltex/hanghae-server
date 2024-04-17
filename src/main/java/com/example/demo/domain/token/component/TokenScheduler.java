package com.example.demo.domain.token.component;

import com.example.demo.domain.token.entity.Token;
import com.example.demo.domain.token.repository.TokenRepository;
import com.example.demo.exception.TicketingErrorCode;
import com.example.demo.exception.TicketingException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.ZonedDateTime;
import java.util.List;

@Component
@RequiredArgsConstructor
public class TokenScheduler {

    private final TokenRepository tokenRepository;
    @Value("${token.maxActiveUser:50}")
    private int MAX_ACTIVE_USER;

    @Scheduled(cron = "0 * * * * *")
    @Transactional
    public void updateToken() {
        int activeCount = tokenRepository.getProgressStatusCount("ACTIVE").intValue();
        int waitCount = tokenRepository.getProgressStatusCount("WAIT").intValue();

        int availableSlots = MAX_ACTIVE_USER - activeCount;
        if(availableSlots>0){
            updateTokensToActive(Math.min(availableSlots, waitCount));
        }
    }

    public void updateTokensToActive(int count) {
        Pageable pageable = PageRequest.of(0, count);
        List<Token> waitListTokens = tokenRepository.findAllTokensToActivate("WAIT", pageable);
        waitListTokens.forEach(this::activateToken);
    }

    public void activateToken(Token token) {
        try {
            token.setExpiredAtAndStatus(ZonedDateTime.now().plusMinutes(10), "ACTIVE");
            tokenRepository.save(token); // 상태 변경을 DB에 반영
        } catch (Exception e) {
            throw new TicketingException(TicketingErrorCode.USER_NOT_FOUND);
        }
    }
    
    @Scheduled(cron = "0 * * * * *")
    @Transactional
    public void dropToken() {
        List<Token> tokenList = tokenRepository.findAllByStatus("ACTIVE");

        for(Token token : tokenList) {
            if(token.getExpiredAt().isBefore(ZonedDateTime.now())) {
                token.expireToken();
            }
        }
    }

}
