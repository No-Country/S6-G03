package com.nocountry.exception;

import java.io.Serial;

public class FileException extends Exception {

    @Serial
    private static final long serialVersionUID = 1L;

    public FileException(String message) {
        super(message);
    }
}