package com.example.demo.controller.concert.dto.response;

import com.example.demo.domain.concert.entity.Show;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;

@NoArgsConstructor
@AllArgsConstructor
public class ShowDateResponse {

    private Long showId;
    private ZonedDateTime showDate;


    public ShowDateResponse(Show show) {
        this.showId=show.getShowId();
        this.showDate=show.getConcertDate();
    }
}
