package com.exadel.sober.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({LoginErrorException.class})
    public ResponseEntity<String> handleLoginErrorException(LoginErrorException ex) {
        return new ResponseEntity(
                ex.getMessage(),
                HttpStatus.UNAUTHORIZED
        );
    }

    @ExceptionHandler({CannotAddReasonException.class,
            CannotAddAddictionException.class,
            CannotAddReasonException.class,
            CannotAddPromiseException.class})
    public ResponseEntity<String> handleAddingException(RuntimeException ex) {
        HttpStatus status = HttpStatus.OK;
        if (ex instanceof LoginErrorException) {
            status = HttpStatus.UNAUTHORIZED;
        }
        return new ResponseEntity(
                ex.getMessage(),
                status
        );
    }

}
