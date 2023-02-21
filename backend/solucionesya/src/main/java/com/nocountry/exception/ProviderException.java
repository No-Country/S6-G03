package com.nocountry.exception;

import java.io.Serial;

public class ProviderException extends Exception {

    @Serial
    private static final long serialVersionUID = 1L;

    public ProviderException(String message) {
        super(message);
    }
}