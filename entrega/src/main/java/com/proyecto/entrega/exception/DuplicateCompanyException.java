package com.proyecto.entrega.exception;

public class DuplicateCompanyException extends RuntimeException {
    public DuplicateCompanyException(String message) {
        super(message);
    }
}