package com.example.demo.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class TicketingExceptionHandler {

    @ExceptionHandler(TicketingException.class)
    protected ResponseEntity<ErrorResponseEntity> handleCustomException(TicketingException e){
        return ErrorResponseEntity.toResponseEntity(e.getTicketingErrorCode());
    }
}
