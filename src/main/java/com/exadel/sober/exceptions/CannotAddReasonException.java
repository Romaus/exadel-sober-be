package com.exadel.sober.exceptions;

public class CannotAddReasonException extends RuntimeException {

    private String message;

    public CannotAddReasonException(String msg) {
        super(msg);
        this.message = msg;
    }
}
