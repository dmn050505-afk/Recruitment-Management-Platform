package com.daniel.recruitment.amodules.exception;

public class UnauthorizedActionException
        extends RuntimeException {

    public UnauthorizedActionException(
            String message
    ) {
        super(message);
    }
}