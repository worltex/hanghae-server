package com.example.demo.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class TicketingException extends RuntimeException{
    TicketingErrorCode ticketingErrorCode;

}
