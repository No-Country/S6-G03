package com.nocountry.exception;

import java.io.Serial;

public class OpinionException extends Exception {

    @Serial
    private static final long serialVersionUID = 1L;

    public OpinionException(String message) {
        super(message);
    }
}