package com.nocountry.exception;

import java.io.Serial;

public class ProvisionException extends Exception {

    @Serial
    private static final long serialVersionUID = 1L;

    public ProvisionException(String message) {
        super(message);
    }
}