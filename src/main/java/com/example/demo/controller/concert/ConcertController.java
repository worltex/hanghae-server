package com.example.demo.controller.concert;

import com.example.demo.aop.RequireValidToken;
import com.example.demo.controller.concert.dto.response.ShowDateResponse;
import com.example.demo.controller.reservation.dto.response.AvailableSeatResponse;
import com.example.demo.domain.concert.entity.Show;
import com.example.demo.domain.concert.service.ConcertService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/concerts")
@RequiredArgsConstructor
public class ConcertController {

    private final ConcertService concertService;

    @Operation(summary = "콘서트 회차 목록 조회")
    @ApiResponse(responseCode = "200", description = "OK", content = @Content(array = @ArraySchema(schema = @Schema(implementation = ShowDateResponse.class))))
    @Parameters({
            @Parameter(name="concertId", required = true, schema=@Schema(type="string"), in= ParameterIn.PATH),
    })
    @RequireValidToken
    @GetMapping("/{concertId}/shows")
    public List<ShowDateResponse> getAvailableShowDates(@PathVariable Long concertId){
        List<Show> showDates = concertService.getShowDates(concertId);
        return showDates.stream().map(ShowDateResponse::new).toList();
    }

    @Operation(summary = "좌석 목록 조회")
    @ApiResponse(responseCode = "200", description = "OK", content = @Content(array = @ArraySchema(schema = @Schema(implementation = AvailableSeatResponse.class))))
    @Parameters({
            @Parameter(name="concertId", required = true, schema=@Schema(type="string"), in= ParameterIn.PATH),
            @Parameter(name="showId", required = true, schema=@Schema(type="string"), in= ParameterIn.PATH)
    })
    @RequireValidToken
    @GetMapping("/{concertId}/shows/{showId}/seats")
    public List<AvailableSeatResponse> getAvailableSeats(@PathVariable Long concertId, @PathVariable Long showId){
       return concertService.getAvailableSeats(concertId, showId);
    }

}
