package com.example.desafiotecnicosicredi.validation;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.desafiotecnicosicredi.client.CPFValidatorService;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class CPFValidator implements ConstraintValidator<CPFValid, String> {

    private boolean enabled = true;

    @Autowired
    CPFValidatorService cpfValidatorService;

    @Override
    public void initialize(CPFValid constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        // Este validador só valida caso houver valor
        if (value == null) {
            return true;
        }

        if (!this.enabled) {
            return true;
        }

        return cpfValidatorService.validarDocumento(value);
    }

    public void enable() {
        this.enabled = true;
    }

    public void disable() {
        this.enabled = false;
    }
}
