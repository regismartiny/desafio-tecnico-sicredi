package com.example.desafiotecnicosicredi.exception;

/**
 * Exception para erros do microsserviço em geral
 */
public class ApplicationException extends RuntimeException {

    public ApplicationException() {
    }

    public ApplicationException(String message) {
        super(message);
    }

    public ApplicationException(String message, Throwable cause) {
        super(message, cause);
    }
}
