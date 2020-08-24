package com.barros.batch.exception;

public class InvalidFieldsException extends RuntimeException  {

    private static final long serialVersionUID = 1L;

    public InvalidFieldsException() {}

    public InvalidFieldsException(String message) {
        super(message);
    }
}