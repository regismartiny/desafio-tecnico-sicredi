package com.example.desafiotecnicosicredi.dto.voto;

import com.example.desafiotecnicosicredi.enums.OpcaoVoto;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class VotoRequestDTO {
    @NotNull
    private OpcaoVoto opcao;
    @NotNull
    private String cpfAssociado;
    @NotNull
    private Long idSessao;
}
