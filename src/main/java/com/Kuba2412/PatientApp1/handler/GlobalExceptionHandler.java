package com.Kuba2412.PatientApp1.handler;

import com.Kuba2412.PatientApp1.handler.exception.PatientNotFound;
import com.Kuba2412.PatientApp1.handler.exception.VisitNotFound;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<String> handleIllegalArgumentException(ResponseStatusException e){
        return ResponseEntity.status(e.getStatusCode()).body(e.getReason());}

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> runtimeException(RuntimeException e){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }

    @ExceptionHandler(PatientNotFound.class)
    public ResponseEntity<String> patientNotFoundException(PatientNotFound e){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }

    @ExceptionHandler(VisitNotFound.class)
    public ResponseEntity<String> visitNotFoundException(VisitNotFound e){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }
}