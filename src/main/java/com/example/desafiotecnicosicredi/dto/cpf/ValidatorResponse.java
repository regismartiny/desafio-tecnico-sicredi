package com.example.desafiotecnicosicredi.dto.cpf;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ValidatorResponse {
    private boolean valid;
    private String formatted;
}
