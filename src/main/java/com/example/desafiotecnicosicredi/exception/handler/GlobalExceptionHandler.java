package com.example.desafiotecnicosicredi.exception.handler;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.desafiotecnicosicredi.configuration.CustomMessageSource;
import com.example.desafiotecnicosicredi.constants.I18Constants;
import com.example.desafiotecnicosicredi.exception.ApplicationException;
import com.example.desafiotecnicosicredi.exception.CPFValidationException;
import com.example.desafiotecnicosicredi.exception.ErrorResponse;

import lombok.extern.slf4j.Slf4j;

@Slf4j(topic = "GLOBAL_EXCEPTION_HANDLER")
@RestControllerAdvice
public class GlobalExceptionHandler {

    @Autowired
    CustomMessageSource messageSource;

    @ResponseStatus(NOT_FOUND)
    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<ErrorResponse> handleNoSuchElementException(Exception e) {
        log.error(e.getMessage());
        return new ResponseEntity<>(new ErrorResponse(NOT_FOUND.value(), e.getLocalizedMessage()), NOT_FOUND);
    }

    @ResponseStatus(BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationExceptions(MethodArgumentNotValidException ex) {
        var response = new ErrorResponse(BAD_REQUEST.value(), messageSource.getMessage(I18Constants.CAMPOS_INVALIDOS.getKey()));
        ex.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            response.addValidationError(fieldName, errorMessage);
        });
        return new ResponseEntity<>(response, BAD_REQUEST);
    }

    @ResponseStatus(BAD_REQUEST)
    @ExceptionHandler(ApplicationException.class)
    public ResponseEntity<ErrorResponse> handleApplicationException(Exception e) {
        log.error(e.getMessage());
        return new ResponseEntity<>(new ErrorResponse(BAD_REQUEST.value(), e.getLocalizedMessage()), BAD_REQUEST);
    }

    @ResponseStatus(BAD_REQUEST)
    @ExceptionHandler(CPFValidationException.class)
    public ResponseEntity<ErrorResponse> handleCPFValidationException(Exception e) {
        log.error(e.getMessage());
        return new ResponseEntity<>(new ErrorResponse(BAD_REQUEST.value(), e.getLocalizedMessage()), BAD_REQUEST);
    }



}
