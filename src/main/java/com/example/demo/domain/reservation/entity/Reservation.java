package com.example.demo.domain.reservation.entity;

import com.example.demo.domain.BaseEntity;
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
    private Long userId;
    private Long concertId;
    private Long showId;
    private Long seatId;
    private String status;
    private ZonedDateTime reservedAt;
    @Version
    private Long version;

    public static Reservation makeReservation(Long concertId, Long showId, Long seatId, Long userId) {
        return Reservation.builder()
                .userId(userId)
                .concertId(concertId)
                .showId(showId)
                .seatId(seatId)
                .status("RESERVING")
                .build();
    }
}
