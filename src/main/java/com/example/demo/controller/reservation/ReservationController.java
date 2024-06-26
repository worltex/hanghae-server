package com.example.demo.controller.reservation;

import com.example.demo.aop.RequireValidToken;
import com.example.demo.controller.reservation.dto.request.ReserveRequest;
import com.example.demo.controller.reservation.dto.response.AvailableSeatResponse;
import com.example.demo.controller.reservation.dto.response.ReserveResponse;
import com.example.demo.domain.reservation.service.ReservationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/shows")
@RequiredArgsConstructor
public class ReservationController {

    @Operation(summary = "좌석 예약")
    @ApiResponse(responseCode = "200", description = "OK", content = @Content(array = @ArraySchema(schema = @Schema(implementation = AvailableSeatResponse.class))))
    @Parameters({
            @Parameter(name="seatId", required = true, schema=@Schema(type="string"), in= ParameterIn.PATH)
    })
    @RequireValidToken
    @PostMapping("/{showId}/seats/{seatId}")
    public ReserveResponse reserveSeat(@PathVariable Long showId, @PathVariable Long seatId, @Valid ReserveRequest request){
        Long userId=request.getUserId();
        Long concertId= request.getConcertId();
        return reservationService.reserveSeat(concertId, showId, seatId, userId);
    }

    private final ReservationService reservationService;
}
