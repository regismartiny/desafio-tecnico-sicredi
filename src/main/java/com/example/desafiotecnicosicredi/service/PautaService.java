package com.example.desafiotecnicosicredi.service;

import static com.example.desafiotecnicosicredi.constants.Constantes.USUARIO;

import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.desafiotecnicosicredi.constants.I18Constants;
import com.example.desafiotecnicosicredi.dto.pauta.consultar.PautaConsultarDTO;
import com.example.desafiotecnicosicredi.dto.pauta.criar.PautaCriarDTO;
import com.example.desafiotecnicosicredi.entity.Pauta;
import com.example.desafiotecnicosicredi.mapper.PautaMapper;
import com.example.desafiotecnicosicredi.repository.PautaRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class PautaService extends ServiceBase {

    @Autowired
    PautaMapper pautaMapper;

    @Autowired
    PautaRepository pautaRepository;

    public void criarPauta(PautaCriarDTO dto) {
        log.info("Cadastrando pauta: {}", dto);
        pautaRepository.save(pautaMapper.toEntity(dto, USUARIO));
    }

    public PautaConsultarDTO consultarPauta(Long id) {
        var pauta = findByIdOrThrow(id);
        return pautaMapper.fromEntity(pauta);
    }

    /**
     * Procurar pauta no repository.
     * Caso não encontre, lança NotFoundException.
     *
     * @param id
     * @return Pauta
     */
    private Pauta findByIdOrThrow(Long id) {
        return pautaRepository.findById(id)
                .orElseThrow(() ->
                        new NoSuchElementException(getLocalMessage(I18Constants.NO_ITEM_FOUND.getKey(), id.toString())));
    }
}