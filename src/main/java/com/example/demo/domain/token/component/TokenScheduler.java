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
public class TokenScheduler {

    private final TokenRepository tokenRepository;
    private final long MAX_ACTIVE=50;

    @Scheduled(cron = "0 * * * * *")
    @Transactional
    public void updateToken() {
        long activeCount = tokenRepository.getProgressStatusCount("ACTIVE");
        long waitCount = tokenRepository.getProgressStatusCount("WAIT");

        int availableSlots = (int) (MAX_ACTIVE - activeCount);
        if(availableSlots>0){
            updateTokensToActive(Math.min(availableSlots, (int) waitCount));
        }

        if(activeCount < MAX_ACTIVE) {
            int availableCount = (int) (MAX_ACTIVE - activeCount);

            for(int i = 0; i<Math.min(availableCount, waitCount); i++) {
                long nextWaitNo = tokenRepository.getNextPriorityWaitNumber("WAIT"); // 가장 우선인 다음 대기순번 고객

                Token token = tokenRepository.findByWaitNo(nextWaitNo).orElseThrow(()->new TicketingException(TicketingErrorCode.USER_NOT_FOUND));

                token.setExpiredAtAndStatus(ZonedDateTime.now().plusMinutes(10)
                        , "ACTIVE");

            }
        }
    }

    public void updateTokensToActive(int count) {
        Pageable pageable = PageRequest.of(0, count);
        List<Token> waitListTokens = tokenRepository.findAllTokensToActivate("WAIT", pageable);
        for (Token token : waitListTokens) {
            activateToken(token);
        }
    }

    private void activateToken(Token token) {
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
