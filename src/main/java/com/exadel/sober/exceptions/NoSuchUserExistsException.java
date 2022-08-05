package com.exadel.sober.exceptions;

public class NoSuchUserExistsException extends RuntimeException{

    private String message;

    public NoSuchUserExistsException (String msg) {
        super(msg);
        this.message = msg;
    }
}
