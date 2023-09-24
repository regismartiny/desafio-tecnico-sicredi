package com.example.desafiotecnicosicredi.dto.pauta;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PautaRequestDTO {
    @NotBlank(message = "Nome é obrigatório")
    private String nome;

    @NotBlank
    private String descricao;
}
