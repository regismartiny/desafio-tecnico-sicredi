package com.example.desafiotecnicosicredi.dto.sessaovotacao;

import java.time.LocalDateTime;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@Builder
@ToString
public class SessaoVotacaoRequestDTO {
    @FutureOrPresent
    private LocalDateTime dataInicio;

    private Long validadeEmMinutos;

    @NotNull
    private Long idPauta;
}
