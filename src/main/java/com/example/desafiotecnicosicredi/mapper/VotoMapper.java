package com.example.desafiotecnicosicredi.mapper;

import org.springframework.stereotype.Component;

import com.example.desafiotecnicosicredi.dto.voto.VotoRequestDTO;
import com.example.desafiotecnicosicredi.dto.voto.VotoResponseDTO;
import com.example.desafiotecnicosicredi.entity.SessaoVotacao;
import com.example.desafiotecnicosicredi.entity.Voto;
import com.example.desafiotecnicosicredi.enums.OpcaoVoto;

@Component
public class VotoMapper {

    public Voto toEntity(VotoRequestDTO dto, SessaoVotacao sessaoVotacao, String usuario) {
        return Voto.builder()
                .cpfAssociado(dto.getCpfAssociado())
                .opcaoVoto(dto.getOpcao().getCodigo())
                .sessaoVotacao(sessaoVotacao)
                .usuario(usuario)
                .build();
    }

    public VotoResponseDTO fromEntity(Voto entity) {
        return VotoResponseDTO.builder()
                .id(entity.getId())
                .cpfAssociado(entity.getCpfAssociado())
                .opcaoVoto(OpcaoVoto.of(entity.getOpcaoVoto()).name())
                .idSessao(entity.getSessaoVotacao().getId())
                .build();
    }
}
