package com.example.desafiotecnicosicredi.service;

import static com.example.desafiotecnicosicredi.constants.Constantes.USUARIO;

import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.example.desafiotecnicosicredi.constants.I18Constants;
import com.example.desafiotecnicosicredi.dto.pauta.PautaRequestDTO;
import com.example.desafiotecnicosicredi.dto.pauta.PautaResponseDTO;
import com.example.desafiotecnicosicredi.dto.sessaovotacao.ContabilizarPautaResponseDTO;
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
     * Caso não existir, será lançada exceção.
     *
     * @param id identificador da pauta
     * @return PautaResponseDTO
     */
    @Cacheable(value = "pauta")
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
                        new NoSuchElementException(getLocalMessage(I18Constants.PAUTA_NAO_ENCONTRADA.getKey(), id.toString())));
    }

    /**
     * Obter contabilização de votos das sessões da Pauta.
     *
     * @param id identificador da pauta
     * @param exibirDetalhamentoSessoes flag que indica se deve ser exibido o detalhamento dos votos das sessões
     * @return ContabilizarPautaResponseDTO
     */
    public ContabilizarPautaResponseDTO contabilizarVotacao(Long id, boolean exibirDetalhamentoSessoes) {
        var pauta = findByIdOrThrow(id);
        return ContabilizarPautaResponseDTO.of(pauta, exibirDetalhamentoSessoes);
    }
}