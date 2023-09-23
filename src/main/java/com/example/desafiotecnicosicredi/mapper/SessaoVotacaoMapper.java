package com.example.desafiotecnicosicredi.mapper;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.desafiotecnicosicredi.configuration.ConfigProperties;
import com.example.desafiotecnicosicredi.dto.sessaovotacao.SessaoVotacaoRequestDTO;
import com.example.desafiotecnicosicredi.dto.sessaovotacao.SessaoVotacaoResponseDTO;
import com.example.desafiotecnicosicredi.entity.Pauta;
import com.example.desafiotecnicosicredi.entity.SessaoVotacao;

@Component
public class SessaoVotacaoMapper {

    @Autowired
    ConfigProperties configProperties;

    public SessaoVotacao toEntity(SessaoVotacaoRequestDTO dto, Pauta pauta, String usuario) {
        return SessaoVotacao.builder()
                .pauta(pauta)
                .dataInicio(obterDataInicio(dto))
                .dataFimValidade(calcularDataFimValidade(dto))
                .usuario(usuario)
                .build();
    }

    private static LocalDateTime obterDataInicio(SessaoVotacaoRequestDTO dto) {
        if (dto.getDataInicio() != null) {
            return dto.getDataInicio();
        }
        return LocalDateTime.now();
    }

    private LocalDateTime calcularDataFimValidade(SessaoVotacaoRequestDTO dto) {
        var dataInicio = obterDataInicio(dto);
        var validadeEmSegundos = dto.getValidadeEmMinutos() != null ? dto.getValidadeEmMinutos() * 60
                : configProperties.getValidadePadraoSessao().toSeconds();
        return dataInicio.plusSeconds(validadeEmSegundos);
    }

    public SessaoVotacaoResponseDTO fromEntity(SessaoVotacao entity) {
        return SessaoVotacaoResponseDTO.builder()
                .id(entity.getId())
                .dataInicio(entity.getDataInicio())
                .dataFimValidade(entity.getDataFimValidade())
                .idPauta(entity.getPauta().getId())
                .build();
    }
}
