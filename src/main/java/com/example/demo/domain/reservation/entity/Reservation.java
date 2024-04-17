package com.example.demo.domain.reservation.entity;

import com.example.demo.domain.BaseEntity;
import com.example.demo.domain.concert.entity.Concert;
import com.example.demo.domain.concert.entity.Seat;
import com.example.demo.domain.concert.entity.Show;
import com.example.demo.domain.user.entity.User;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

import java.time.ZonedDateTime;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DynamicUpdate
@Getter
@AllArgsConstructor
@Builder
public class Reservation extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reservationId;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "concert_id")
    private Concert concert;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "show_id")
    private Show show;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "seat_id")
    private Seat seat;
    private String status;
    private ZonedDateTime reservedAt;
    @Version
    private Long version;

    public static Reservation makeReservation(Concert concert, Show show, Seat seat, User user) {
        return Reservation.builder()
                .concert(concert)
                .show(show)
                .seat(seat)
                .user(user)
                .status("RESERVING")
                .build();
    }
}
