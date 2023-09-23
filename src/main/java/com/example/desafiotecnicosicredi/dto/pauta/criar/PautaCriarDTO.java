package com.example.desafiotecnicosicredi.dto.pauta.criar;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class PautaCriarDTO {
    @NotBlank(message = "Nome é obrigatório")
    private String nome;
    @NotBlank
    private String descricao;
}
