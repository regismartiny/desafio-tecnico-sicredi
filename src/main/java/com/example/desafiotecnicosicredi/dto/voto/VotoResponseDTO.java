package com.example.desafiotecnicosicredi.dto.voto;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@Builder
@ToString
public class VotoResponseDTO {
    private Long id;
    private String opcaoVoto;
    private String cpfAssociado;
    private Long idSessao;
}
