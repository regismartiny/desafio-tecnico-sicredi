package com.example.desafiotecnicosicredi.dto.pauta;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PautaResponseDTO {
    private Long id;
    private String nome;
    private String descricao;
}
