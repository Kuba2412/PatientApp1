package com.Kuba2412.PatientApp1.handler.exception;

import org.springframework.http.HttpStatus;

public class VisitNotFound extends PatientException {

    public VisitNotFound(String message) {
        super(message, HttpStatus.NOT_FOUND);
    }
}