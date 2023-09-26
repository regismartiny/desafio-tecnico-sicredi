package com.example.desafiotecnicosicredi.dto.sessaovotacao;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

import com.example.desafiotecnicosicredi.dto.pauta.TotalizacaoVotosPautaDTO;
import com.example.desafiotecnicosicredi.entity.Pauta;
import com.example.desafiotecnicosicredi.entity.SessaoVotacao;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@Builder
@ToString
public class ContabilizarPautaResponseDTO {
    private String nomePauta;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<ContabilizacaoSessaoVotacaoDTO> contabilizacaoSessoes;

    private List<TotalizacaoVotosPautaDTO> totalizacaoDeVotos;

    public static ContabilizarPautaResponseDTO of(Pauta pauta, boolean exibirDetalhamentoSessoes) {
        List<ContabilizacaoSessaoVotacaoDTO> contabilizacaoSessoes = buildContabilizacaoSessoes(pauta);
        ContabilizarPautaResponseDTO contabilizacao = ContabilizarPautaResponseDTO.builder()
                .nomePauta(pauta.getNome())
                .totalizacaoDeVotos(buildTotalizacaoVotos(contabilizacaoSessoes))
                .build();

        if (exibirDetalhamentoSessoes) {
            contabilizacao.setContabilizacaoSessoes(contabilizacaoSessoes);
        }

        return contabilizacao;
    }

    private static List<TotalizacaoVotosPautaDTO> buildTotalizacaoVotos(List<ContabilizacaoSessaoVotacaoDTO> contabilizacaoSessoes) {
        HashMap<String, Long> mapaOpcoesEQuantidade = new HashMap<>();
        List<VotosContabilizadosResponseDTO> votosContabilizados = contabilizacaoSessoes.stream()
                .map(ContabilizacaoSessaoVotacaoDTO::getVotosContabilizados)
                .flatMap(List::stream)
                .toList();

        votosContabilizados
                .forEach(votoContabilizado -> {
                    String opcao = votoContabilizado.getOpcao();
                    Long totalAtual = Optional.ofNullable(mapaOpcoesEQuantidade.get(opcao)).orElse(0L);
                    Long quantidade = votoContabilizado.getQuantidade();
                    mapaOpcoesEQuantidade.put(opcao, totalAtual + quantidade);
                });

        return mapaOpcoesEQuantidade.entrySet().stream()
                .map(buildTotalizacaoVotosPauta())
                .toList();
    }

    private static Function<Map.Entry<String, Long>, TotalizacaoVotosPautaDTO> buildTotalizacaoVotosPauta() {
        return entrada -> TotalizacaoVotosPautaDTO.builder()
                .opcao(entrada.getKey())
                .quantidade(entrada.getValue())
                .build();
    }

    private static List<ContabilizacaoSessaoVotacaoDTO> buildContabilizacaoSessoes(Pauta pauta) {
        return pauta.getSessoesVotacao().stream()
                .sorted(Comparator.comparing(SessaoVotacao::getDataInicio))
                .map(ContabilizacaoSessaoVotacaoDTO::of)
                .toList();
    }
}
