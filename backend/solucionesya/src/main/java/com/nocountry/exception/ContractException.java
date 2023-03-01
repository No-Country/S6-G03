package com.nocountry.exception;

import java.io.Serial;

public class ContractException extends Exception {

    @Serial
    private static final long serialVersionUID = 1L;

    public ContractException(String message) {
        super(message);
    }
}