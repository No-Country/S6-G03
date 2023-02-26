package com.nocountry.exception;

import java.io.Serial;

public class AdminException extends Exception {

    @Serial
    private static final long serialVersionUID = 1L;

    public AdminException(String message) {
        super(message);
    }
}