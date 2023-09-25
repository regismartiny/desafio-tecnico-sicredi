package com.example.desafiotecnicosicredi.endpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.desafiotecnicosicredi.dto.sessaovotacao.ContabilizarSessaoVotacaoResponseDTO;
import com.example.desafiotecnicosicredi.dto.sessaovotacao.SessaoVotacaoRequestDTO;
import com.example.desafiotecnicosicredi.dto.sessaovotacao.SessaoVotacaoResponseDTO;
import com.example.desafiotecnicosicredi.service.SessaoVotacaoService;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/v1/sessao-votacao")
public class SessaoVotacaoResource {

    @Autowired
    SessaoVotacaoService sessaoVotacaoService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Criar sessão de votação")
    public SessaoVotacaoResponseDTO criarSessaoVotacao(@Valid @RequestBody SessaoVotacaoRequestDTO pauta) {
        return sessaoVotacaoService.criarSessaoVotacao(pauta);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar sessão de votação pelo id")
    public SessaoVotacaoResponseDTO buscarSessaoVotacao(@PathVariable Long id) {
        return sessaoVotacaoService.consultarSessaoVotacao(id);
    }
    @GetMapping("/{id}/contabilizar")
    @Operation(summary = "Contabilizar votos da sessão")
    public ContabilizarSessaoVotacaoResponseDTO contabilizarSessaoVotacao(@PathVariable Long id) {
        return sessaoVotacaoService.contabilizarSessaoVotacao(id);
    }

}
