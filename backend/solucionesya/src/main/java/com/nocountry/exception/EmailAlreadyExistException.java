package com.nocountry.exception;

import java.io.Serial;

public class EmailAlreadyExistException extends Exception {

    @Serial
    private static final long serialVersionUID = 1L;

    public EmailAlreadyExistException(String message) {
        super(message);
    }
}
