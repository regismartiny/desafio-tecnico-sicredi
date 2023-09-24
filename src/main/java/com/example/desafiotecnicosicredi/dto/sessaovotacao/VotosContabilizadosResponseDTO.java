package com.example.desafiotecnicosicredi.dto.sessaovotacao;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@Builder
@ToString
public class VotosContabilizadosResponseDTO {
    private String opcao;
    private Long quantidade;
}
