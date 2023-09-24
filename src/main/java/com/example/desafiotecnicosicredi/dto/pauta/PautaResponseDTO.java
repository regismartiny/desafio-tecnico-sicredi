package com.example.desafiotecnicosicredi.dto.pauta;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@Builder
@ToString
public class PautaResponseDTO {
    private Long id;
    private String nome;
    private String descricao;
}
