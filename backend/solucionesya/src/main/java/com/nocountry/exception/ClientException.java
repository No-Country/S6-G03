package com.nocountry.exception;

import java.io.Serial;

public class ClientException extends Exception {

    @Serial
    private static final long serialVersionUID = 1L;

    public ClientException(String message) {
        super(message);
    }
}