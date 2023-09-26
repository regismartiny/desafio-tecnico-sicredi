package com.example.desafiotecnicosicredi.dto.sessaovotacao;

import com.example.desafiotecnicosicredi.entity.SessaoVotacao;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@Builder
@ToString
public class ContabilizarSessaoVotacaoResponseDTO {
    private String nomePauta;
    private ContabilizacaoSessaoVotacaoDTO contabilizacaoSessao;

    public static ContabilizarSessaoVotacaoResponseDTO of (SessaoVotacao sessaoVotacao) {
        return ContabilizarSessaoVotacaoResponseDTO.builder()
                .nomePauta(sessaoVotacao.getPauta().getNome())
                .contabilizacaoSessao(ContabilizacaoSessaoVotacaoDTO.of(sessaoVotacao))
                .build();
    }
}
