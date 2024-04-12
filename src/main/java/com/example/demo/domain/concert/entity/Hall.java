package com.example.demo.domain.concert.entity;

import com.example.demo.domain.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DynamicUpdate
public class Hall extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long hallId;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private int seatCount;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "seat_id")
    private List<Seat> seatList = new ArrayList();
}
