package com.example.desafiotecnicosicredi.dto.voto;

import com.example.desafiotecnicosicredi.enums.OpcaoVoto;
import com.example.desafiotecnicosicredi.validation.CPFValid;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@Builder
@ToString
public class VotoRequestDTO {
    @NotNull
    private OpcaoVoto opcao;

    @NotNull
    @CPFValid
    private String cpfAssociado;

    @NotNull
    private Long idSessao;
}
