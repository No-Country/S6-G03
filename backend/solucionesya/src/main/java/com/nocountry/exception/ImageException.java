package com.nocountry.exception;

import java.io.Serial;

public class ImageException extends Exception {

    @Serial
    private static final long serialVersionUID = 1L;

    public ImageException(String message) {
        super(message);
    }
}