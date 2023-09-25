package com.example.desafiotecnicosicredi.dto.pauta;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
public class PautaRequestDTO {
    @NotBlank
    private String nome;

    @NotBlank
    private String descricao;
}
