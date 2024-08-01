package com.Kuba2412.PatientApp1.handler.exception;

import org.springframework.http.HttpStatus;

public class PatientException extends RuntimeException {

    private HttpStatus httpStatus;

    public PatientException(String message, HttpStatus httpStatus) {
        super(message);
        this.httpStatus = httpStatus;
    }

    public HttpStatus getStatus() {
        return httpStatus;
    }
}





