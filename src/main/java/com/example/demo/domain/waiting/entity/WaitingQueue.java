package com.example.demo.domain.waiting.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

import java.sql.Timestamp;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DynamicUpdate
public class WaitingQueue {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long waitingQueueId;

    @Column(nullable = false)
    private Long userId;

    @Column(nullable = false)
    private String token;

    private String status;

    private Timestamp activeTime;
    private Timestamp requestTime;
}
