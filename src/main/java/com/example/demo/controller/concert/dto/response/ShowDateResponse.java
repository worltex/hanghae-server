package com.example.demo.controller.concert.dto.response;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;

@NoArgsConstructor
@AllArgsConstructor
public class ShowDateResponse {

    private String showId;
    private ZonedDateTime showDate;
}
