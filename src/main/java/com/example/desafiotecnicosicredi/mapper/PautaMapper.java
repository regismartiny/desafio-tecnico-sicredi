package com.example.desafiotecnicosicredi.mapper;

import org.springframework.stereotype.Component;

import com.example.desafiotecnicosicredi.dto.pauta.consultar.PautaConsultarDTO;
import com.example.desafiotecnicosicredi.dto.pauta.criar.PautaCriarDTO;
import com.example.desafiotecnicosicredi.entity.Pauta;

@Component
public class PautaMapper {

    public Pauta toEntity(PautaCriarDTO dto, String usuario) {
        return Pauta.builder()
                .nome(dto.getNome())
                .descricao(dto.getDescricao())
                .usuario(usuario)
                .build();
    }

    public PautaConsultarDTO fromEntity(Pauta entity) {
        return PautaConsultarDTO.builder()
                .nome(entity.getNome())
                .descricao(entity.getDescricao())
                .build();
    }
}
