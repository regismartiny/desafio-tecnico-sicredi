package com.example.desafiotecnicosicredi.exception;

/**
 * Exception para erros do serviço de validação de CPF
 */
public class CPFValidationException extends RuntimeException {

    public CPFValidationException() {
    }

    public CPFValidationException(String message) {
        super(message);
    }

    public CPFValidationException(String message, Throwable cause) {
        super(message, cause);
    }
}
