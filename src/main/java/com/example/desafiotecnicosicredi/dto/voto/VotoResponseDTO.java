package com.example.desafiotecnicosicredi.dto.voto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class VotoResponseDTO {
    private Long id;
    private String opcaoVoto;
    private String cpfAssociado;
    private Long idSessao;
}
