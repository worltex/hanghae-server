package com.example.demo.aop;

import com.example.demo.domain.token.component.TokenValidator;
import com.example.demo.domain.token.service.TokenService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;


@Aspect
@Component
@RequiredArgsConstructor
public class TokenAspect {

    @Pointcut("@annotation(RequireValidToken)")
    public void requireValidToken(){}

    private final TokenValidator tokenValidator;

    @Before("requireValidToken()")
    public void preProcess(JoinPoint joinPoint) throws Throwable{
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        String token = request.getHeader("Authorization");
        if(token ==null || !token.startsWith("Bearer ")){
            throw new RuntimeException("토큰이 유효하지 않습니다.");
        }
        tokenValidator.isValid(token);
    }
}
