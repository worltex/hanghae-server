package com.example.demo.controller.reservation;

import com.example.demo.controller.reservation.dto.response.AvailableSeatResponse;
import com.example.demo.domain.reservation.service.ReservationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/shows")
@RequiredArgsConstructor
public class ReservationController {

    private final ReservationService reservationService;

    @Operation(summary = "좌석 예약")
    @ApiResponse(responseCode = "200", description = "OK", content = @Content(array = @ArraySchema(schema = @Schema(implementation = AvailableSeatResponse.class))))
    @Parameters({
            @Parameter(name="seatId", required = true, schema=@Schema(type="string"), in= ParameterIn.PATH)
    })
    @PostMapping("/{showId}/seats/{seatId}")
    public ResponseEntity<Void> reserveSeat(@PathVariable String seatId){
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
