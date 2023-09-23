package com.example.desafiotecnicosicredi.dto.pauta.consultar;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PautaConsultarDTO {
    private String nome;
    private String descricao;
}
