package com.example.desafiotecnicosicredi.dto.pauta;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@Builder
@ToString
public class TotalizacaoVotosPautaDTO {
    private String opcao;
    private Long quantidade;
}
