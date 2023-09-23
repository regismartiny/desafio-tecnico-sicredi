package com.example.desafiotecnicosicredi.service;

import static com.example.desafiotecnicosicredi.constants.Constantes.USUARIO;

import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.desafiotecnicosicredi.constants.I18Constants;
import com.example.desafiotecnicosicredi.dto.sessaovotacao.SessaoVotacaoResponseDTO;
import com.example.desafiotecnicosicredi.dto.sessaovotacao.SessaoVotacaoRequestDTO;
import com.example.desafiotecnicosicredi.entity.SessaoVotacao;
import com.example.desafiotecnicosicredi.mapper.SessaoVotacaoMapper;
import com.example.desafiotecnicosicredi.repository.SessaoVotacaoRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class SessaoVotacaoService extends ServiceBase {

    @Autowired
    SessaoVotacaoMapper sessaoVotacaoMapper;

    @Autowired
    SessaoVotacaoRepository sessaoVotacaoRepository;

    @Autowired
    PautaService pautaService;

    public SessaoVotacaoResponseDTO criarSessaoVotacao(SessaoVotacaoRequestDTO dto) {
        log.info("Cadastrando sessão de votação: {}", dto);
        var pauta = pautaService.findByIdOrThrow(dto.getIdPauta());
        var sessaoVotacao = sessaoVotacaoMapper.toEntity(dto, pauta, USUARIO);
        sessaoVotacao = sessaoVotacaoRepository.save(sessaoVotacao);
        return sessaoVotacaoMapper.fromEntity(sessaoVotacao);
    }

    public SessaoVotacaoResponseDTO consultarSessaoVotacao(Long id) {
        var sessaoVotacao = findByIdOrThrow(id);
        return sessaoVotacaoMapper.fromEntity(sessaoVotacao);
    }

    /**
     * Procurar sessão de votação no repository.
     * Caso não encontre, lança NoSuchElementException.
     *
     * @param id
     * @return SessaoVotacao
     */
    private SessaoVotacao findByIdOrThrow(Long id) {
        return sessaoVotacaoRepository.findById(id)
                .orElseThrow(() ->
                        new NoSuchElementException(getLocalMessage(I18Constants.NO_ITEM_FOUND.getKey(), id.toString())));
    }
}