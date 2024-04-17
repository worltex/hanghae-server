package com.example.demo.domain.token.entity;

import com.example.demo.domain.user.entity.User;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;

import java.time.ZonedDateTime;

@Getter
@Entity
@Builder
public class Token {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="token_id")
    private long id;

    @Column(name="token")
    private String token;

    @Column(name="status")
    private String status;

    @Column(name="wait_no")
    private long waitNo;

    @Column(name="created_at")
    private ZonedDateTime createdAt;

    @Column(name="updated_at")
    private ZonedDateTime updatedAt;

    @Column(name="expired_at")
    private ZonedDateTime expiredAt;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public void setExpiredAtAndStatus(ZonedDateTime expiredAt, String status) {
        this.expiredAt=expiredAt;
        this.status=status;
    }

    public void expireToken() {
        this.status="EXPIRED";
        this.updatedAt=ZonedDateTime.now();
    }
}
