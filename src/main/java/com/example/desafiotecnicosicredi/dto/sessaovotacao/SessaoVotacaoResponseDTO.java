package com.example.desafiotecnicosicredi.dto.sessaovotacao;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@Builder
@ToString
public class SessaoVotacaoResponseDTO {
    private Long id;
    private LocalDateTime dataInicio;
    private LocalDateTime dataFimValidade;
    private Long idPauta;
}
