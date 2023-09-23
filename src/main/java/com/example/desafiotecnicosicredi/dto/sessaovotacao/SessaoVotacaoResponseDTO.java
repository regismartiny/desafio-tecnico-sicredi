package com.example.desafiotecnicosicredi.dto.sessaovotacao;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SessaoVotacaoResponseDTO {
    private Long id;
    private LocalDateTime dataInicio;
    private LocalDateTime dataFimValidade;
    private Long idPauta;
}
