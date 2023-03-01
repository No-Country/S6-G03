package com.nocountry.exception;

import java.io.Serial;

public class CloudinaryException extends Exception {

    @Serial
    private static final long serialVersionUID = 1L;

    public CloudinaryException(String message) {
        super(message);
    }
}