package com.example.demo.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum TicketingErrorCode {

    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "TICKETING_ERROR_01", "사용자를 찾을 수 없습니다."),
    ALREADY_RESERVED(HttpStatus.BAD_REQUEST, "TICKETING_ERROR_02", "이미 등록된 좌석입니다."),
    ;

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;
}
