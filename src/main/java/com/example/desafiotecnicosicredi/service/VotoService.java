package com.example.desafiotecnicosicredi.service;

import static com.example.desafiotecnicosicredi.constants.Constantes.USUARIO;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.desafiotecnicosicredi.constants.I18Constants;
import com.example.desafiotecnicosicredi.dto.voto.VotoRequestDTO;
import com.example.desafiotecnicosicredi.dto.voto.VotoResponseDTO;
import com.example.desafiotecnicosicredi.entity.SessaoVotacao;
import com.example.desafiotecnicosicredi.entity.Voto;
import com.example.desafiotecnicosicredi.exception.ApplicationException;
import com.example.desafiotecnicosicredi.mapper.VotoMapper;
import com.example.desafiotecnicosicredi.repository.VotoRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class VotoService extends ServiceBase {

    @Autowired
    VotoMapper votoMapper;

    @Autowired
    VotoRepository votoRepository;

    @Autowired
    SessaoVotacaoService sessaoVotacaoService;

    /**
     * Registrar um voto.
     * Sessão de votação informada deve existir e estar aberta.
     *
     * @param dto corpo da requisicao
     * @return VotoResponseDTO
     */
    public VotoResponseDTO registrarVoto(VotoRequestDTO dto) {
        log.info("Registrando voto: {}", dto);
        var sessaoVotacao = sessaoVotacaoService.findByIdOrThrow(dto.getIdSessao());
        validarSessaoVotacaoAberta(sessaoVotacao);
        validarAssociadoAindaNaoVotou(dto, sessaoVotacao);
        var voto = votoMapper.toEntity(dto, sessaoVotacao, USUARIO);
        voto = votoRepository.save(voto);
        return votoMapper.fromEntity(voto);
    }

    private void validarAssociadoAindaNaoVotou(VotoRequestDTO dto, SessaoVotacao sessaoVotacao) {
        if (votoRepository.verificarSeUsuarioJaVotou(dto.getCpfAssociado(), sessaoVotacao.getPauta().getId() )) {
            throw new ApplicationException(getLocalMessage(I18Constants.ASSOCIADO_JA_VOTOU.getKey()));
        }
    }

    private void validarSessaoVotacaoAberta(SessaoVotacao sessaoVotacao) {
        var dataAtual = LocalDateTime.now();
        if (dataAtual.isBefore(sessaoVotacao.getDataInicio())) {
            throw new ApplicationException(getLocalMessage(I18Constants.SESSAO_NAO_INICIOU.getKey()));
        }
        if (dataAtual.isAfter(sessaoVotacao.getDataFimValidade())) {
            throw new ApplicationException(getLocalMessage(I18Constants.SESSAO_ENCERRADA.getKey()));
        }
    }

    /**
     * Consultar voto pelo id.
     *
     * @param id identificador do voto
     * @return VotoResponseDTO
     */
    public VotoResponseDTO consultarVoto(Long id) {
        var voto = findByIdOrThrow(id);
        return votoMapper.fromEntity(voto);
    }

    /**
     * Procurar voto no repository.
     * Caso não encontre, lança NoSuchElementException.
     *
     * @param id identificador do voto
     * @return Voto
     */
    public Voto findByIdOrThrow(Long id) {
        return votoRepository.findById(id)
                .orElseThrow(() ->
                        new NoSuchElementException(getLocalMessage(I18Constants.REGISTRO_NAO_ENCONTRADO.getKey(), id.toString())));
    }
}