package com.example.desafiotecnicosicredi.endpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.desafiotecnicosicredi.dto.sessaovotacao.SessaoVotacaoRequestDTO;
import com.example.desafiotecnicosicredi.dto.sessaovotacao.SessaoVotacaoResponseDTO;
import com.example.desafiotecnicosicredi.service.SessaoVotacaoService;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/v1/sessaovotacao")
public class SessaoVotacaoResource {

    @Autowired
    SessaoVotacaoService sessaoVotacaoService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Criar sessão de votação")
    public ResponseEntity<SessaoVotacaoResponseDTO> criarSessaoVotacao(@Valid @RequestBody SessaoVotacaoRequestDTO pauta) {
        return ResponseEntity.ofNullable(sessaoVotacaoService.criarSessaoVotacao(pauta));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar sessão de votação pelo id")
    public ResponseEntity<SessaoVotacaoResponseDTO> buscarSessaoVotacao(@PathVariable Long id) {
        return ResponseEntity.ofNullable(sessaoVotacaoService.consultarSessaoVotacao(id));
    }
}
