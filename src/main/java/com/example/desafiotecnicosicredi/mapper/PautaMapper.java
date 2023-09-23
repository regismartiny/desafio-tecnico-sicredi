package com.example.desafiotecnicosicredi.mapper;

import org.springframework.stereotype.Component;

import com.example.desafiotecnicosicredi.dto.pauta.PautaRequestDTO;
import com.example.desafiotecnicosicredi.dto.pauta.PautaResponseDTO;
import com.example.desafiotecnicosicredi.entity.Pauta;

@Component
public class PautaMapper {

    public Pauta toEntity(PautaRequestDTO dto, String usuario) {
        return Pauta.builder()
                .nome(dto.getNome())
                .descricao(dto.getDescricao())
                .usuario(usuario)
                .build();
    }

    public PautaResponseDTO fromEntity(Pauta entity) {
        return PautaResponseDTO.builder()
                .id(entity.getId())
                .nome(entity.getNome())
                .descricao(entity.getDescricao())
                .build();
    }
}
