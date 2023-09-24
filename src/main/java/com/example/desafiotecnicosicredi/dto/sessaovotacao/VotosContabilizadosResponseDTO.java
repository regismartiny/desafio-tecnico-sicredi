package com.example.desafiotecnicosicredi.dto.sessaovotacao;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class VotosContabilizadosResponseDTO {
    private String opcao;
    private Long quantidade;
}
