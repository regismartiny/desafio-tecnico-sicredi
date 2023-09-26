package com.example.desafiotecnicosicredi.dto.pauta;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.ToString;

@Data
@Builder
@ToString
public class PautaRequestDTO {
    @NotBlank
    private String nome;

    @NotBlank
    private String descricao;
}
