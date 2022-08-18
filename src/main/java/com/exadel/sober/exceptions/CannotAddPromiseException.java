package com.exadel.sober.exceptions;

public class CannotAddPromiseException extends RuntimeException {

    private String message;

    public CannotAddPromiseException(String msg) {
        super(msg);
        this.message = msg;
    }
}
