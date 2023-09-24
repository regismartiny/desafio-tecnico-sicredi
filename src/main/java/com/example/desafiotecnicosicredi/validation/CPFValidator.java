package com.example.desafiotecnicosicredi.validation;

import com.example.desafiotecnicosicredi.utils.CPFUtil;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class CPFValidator implements ConstraintValidator<CPFValid, String> {

    private boolean enabled = true;

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

        return CPFUtil.isCPF(value);
    }

    public void enable() {
        this.enabled = true;
    }

    public void disable() {
        this.enabled = false;
    }
}
