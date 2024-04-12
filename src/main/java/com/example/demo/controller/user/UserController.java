package com.example.demo.controller.user;

import com.example.demo.controller.reservation.dto.response.AvailableSeatResponse;
import com.example.demo.controller.user.dto.request.UserBalanceUpdateRequest;
import com.example.demo.controller.user.dto.response.UserBalanceResponse;
import com.example.demo.controller.user.dto.response.UserPaymentResponse;
import com.example.demo.domain.payment.service.PaymentService;
import com.example.demo.domain.user.service.UserService;
import com.example.demo.domain.waiting.service.WaitingQueueService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final WaitingQueueService waitingQueueService;
    private final UserService userService;
    private final PaymentService paymentService;

    @Operation(summary = "토큰 발급")
    @ApiResponse(responseCode = "200", description = "OK", content = @Content(array = @ArraySchema(schema = @Schema(implementation = AvailableSeatResponse.class))))
    @Parameters({
            @Parameter(name="userId", required = true, schema=@Schema(type="string"), in= ParameterIn.PATH)
    })
    @PostMapping("/{userId}/token")
    public String createToken(@PathVariable Long userId){
        return "token";
    }

    @Operation(summary = "잔액 조회")
    @ApiResponse(responseCode = "200", description = "OK", content = @Content(array = @ArraySchema(schema = @Schema(implementation = AvailableSeatResponse.class))))
    @Parameters({
            @Parameter(name="userId", required = true, schema=@Schema(type="string"), in= ParameterIn.PATH)
    })
    @GetMapping("/{userId}/balance")
    public UserBalanceResponse getBalance(@PathVariable Long userId){
        return userService.getBalance(userId);
    }

    @Operation(summary = "잔고 충전")
    @ApiResponse(responseCode = "200", description = "OK", content = @Content(array = @ArraySchema(schema = @Schema(implementation = AvailableSeatResponse.class))))
    @Parameters({
            @Parameter()
    })
    @PatchMapping("/{userId}/balance")
    public UserBalanceResponse chargeBalance(@PathVariable Long userId, UserBalanceUpdateRequest request){
        return userService.chargeBalance(userId, request.getAmount());
    }

    @Operation(summary = "좌석 결제")
    @ApiResponse(responseCode = "200", description = "OK", content = @Content(array = @ArraySchema(schema = @Schema(implementation = AvailableSeatResponse.class))))
    @PostMapping("/{userId}/payments/{paymentId}")
    public UserPaymentResponse pay(@PathVariable Long userId, @PathVariable Long paymentId){
        return paymentService.pay(userId, paymentId);
    }
}
