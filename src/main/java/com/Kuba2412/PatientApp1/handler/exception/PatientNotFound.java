package com.Kuba2412.PatientApp1.handler.exception;

import org.springframework.http.HttpStatus;

public class PatientNotFound extends PatientException {

    public PatientNotFound(String message) {
        super(message, HttpStatus.NOT_FOUND);
    }
}