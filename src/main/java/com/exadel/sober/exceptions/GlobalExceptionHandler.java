package com.exadel.sober.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({NoSuchUserExistsException.class,
            UserAlreadyExistsException.class,
            BadUserCredentialException.class})
    public ResponseEntity<String> handleException(RuntimeException ex) {
        return new ResponseEntity(
                ex.getMessage(),
                HttpStatus.UNAUTHORIZED
        );
    }
}
