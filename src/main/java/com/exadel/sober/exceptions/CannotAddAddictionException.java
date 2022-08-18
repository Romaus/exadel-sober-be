package com.exadel.sober.exceptions;

public class CannotAddAddictionException extends RuntimeException {

    private String message;

    public CannotAddAddictionException(String msg) {
        super(msg);
        this.message = msg;
    }
}
