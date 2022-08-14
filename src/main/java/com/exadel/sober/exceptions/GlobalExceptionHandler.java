package com.exadel.sober.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({NoSuchUserExistsException.class,
            UserAlreadyExistsException.class,
            BadUserCredentialException.class,
            CannotAddReasonException.class,
            CannotAddAddictionException.class,
            CannotAddReasonException.class,
            CannotAddPromiseException.class})
    public ResponseEntity<String> handleException(RuntimeException ex) {
        HttpStatus status = HttpStatus.OK;
        if (ex instanceof NoSuchUserExistsException |
                ex instanceof UserAlreadyExistsException |
                ex instanceof BadUserCredentialException) {
            status = HttpStatus.UNAUTHORIZED;
        }
        return new ResponseEntity(
                ex.getMessage(),
                status
        );
    }

}
