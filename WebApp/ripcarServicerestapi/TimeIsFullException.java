package com.example.ripcarServicerestapi;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "Time is full")
public class TimeIsFullException extends Exception{
    public TimeIsFullException(String errorMessage) {
        super(errorMessage);
    }
}
