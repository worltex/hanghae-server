package com.example.demo.domain.waiting.service;

import com.example.demo.domain.token.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WaitingQueueService {

    private final JwtService jwtService;
}
