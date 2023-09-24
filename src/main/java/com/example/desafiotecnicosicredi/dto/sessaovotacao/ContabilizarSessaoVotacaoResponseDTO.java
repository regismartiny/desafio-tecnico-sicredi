package com.example.desafiotecnicosicredi.dto.sessaovotacao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.example.desafiotecnicosicredi.entity.SessaoVotacao;
import com.example.desafiotecnicosicredi.enums.OpcaoVoto;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@Builder
@ToString
public class ContabilizarSessaoVotacaoResponseDTO {
    private String nomePauta;
    private String situacaoSessao;
    private List<VotosContabilizadosResponseDTO> votosContabilizados;

    public static ContabilizarSessaoVotacaoResponseDTO of(SessaoVotacao sessaoVotacao) {
        return ContabilizarSessaoVotacaoResponseDTO.builder()
                .nomePauta(sessaoVotacao.getPauta().getNome())
                .situacaoSessao(sessaoVotacao.getSituacaoSessao().name())
                .votosContabilizados(obterVotosContabilizados(sessaoVotacao))
                .build();
    }

    private static List<VotosContabilizadosResponseDTO> obterVotosContabilizados(SessaoVotacao sessaoVotacao) {
        var votos = sessaoVotacao.getVotos();
        var votosContabilizados = new HashMap<Integer, Long>();
        votos.forEach(voto -> {
            Integer opcaoVoto = voto.getOpcaoVoto();
            Long contagemDeVotos = Optional.ofNullable(votosContabilizados.get(opcaoVoto)).orElse(0L);
            votosContabilizados.put(opcaoVoto, contagemDeVotos + 1);
        });

        return votosContabilizados.entrySet().stream()
                .map(ContabilizarSessaoVotacaoResponseDTO::buildVotosContabilizadosDaSessaoResponseDTO)
                .toList();
    }

    private static VotosContabilizadosResponseDTO buildVotosContabilizadosDaSessaoResponseDTO(Map.Entry<Integer, Long> entry) {
        return VotosContabilizadosResponseDTO.builder()
                .opcao(OpcaoVoto.of(entry.getKey()).name())
                .quantidade(entry.getValue())
                .build();
    }
}
