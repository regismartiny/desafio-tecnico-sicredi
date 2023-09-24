package com.example.desafiotecnicosicredi.service;

import static com.example.desafiotecnicosicredi.constants.Constantes.USUARIO;

import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.desafiotecnicosicredi.constants.I18Constants;
import com.example.desafiotecnicosicredi.dto.pauta.PautaRequestDTO;
import com.example.desafiotecnicosicredi.dto.pauta.PautaResponseDTO;
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

    /**
     * Criar uma pauta de votação.
     *
     * @param dto corpo da requisicao
     * @return PautaResponseDTO
     */
    public PautaResponseDTO criarPauta(PautaRequestDTO dto) {
        log.info("Cadastrando pauta: {}", dto);
        var pauta = pautaMapper.toEntity(dto, USUARIO);
        pauta = pautaRepository.save(pauta);
        return pautaMapper.fromEntity(pauta);
    }

    /**
     * Consultar pauta de votação pelo id.
     *
     * @param id identificador da pauta
     * @return PautaResponseDTO
     */
    public PautaResponseDTO consultarPauta(Long id) {
        var pauta = findByIdOrThrow(id);
        return pautaMapper.fromEntity(pauta);
    }

    /**
     * Procurar pauta no repository.
     * Caso não encontre, lança NoSuchElementException.
     *
     * @param id identificador da pauta
     * @return Pauta
     */
    public Pauta findByIdOrThrow(Long id) {
        return pautaRepository.findById(id)
                .orElseThrow(() ->
                        new NoSuchElementException(getLocalMessage(I18Constants.REGISTRO_NAO_ENCONTRADO.getKey(), id.toString())));
    }
}