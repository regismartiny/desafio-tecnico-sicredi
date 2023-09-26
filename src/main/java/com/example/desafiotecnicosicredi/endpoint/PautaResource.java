package com.example.desafiotecnicosicredi.endpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.desafiotecnicosicredi.dto.pauta.PautaRequestDTO;
import com.example.desafiotecnicosicredi.dto.pauta.PautaResponseDTO;
import com.example.desafiotecnicosicredi.dto.sessaovotacao.ContabilizarPautaResponseDTO;
import com.example.desafiotecnicosicredi.service.PautaService;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/v1/pauta")
public class PautaResource {

    @Autowired
    PautaService pautaService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Criar pauta")
    public PautaResponseDTO criarPauta(@Valid @RequestBody PautaRequestDTO pauta) {
        return pautaService.criarPauta(pauta);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar pauta pelo id")
    public PautaResponseDTO buscarPauta(@PathVariable Long id) {
        return pautaService.consultarPauta(id);
    }

    @GetMapping("/{id}/contabilizar")
    @Operation(summary = "Contabilizar votos da pauta")
    public ContabilizarPautaResponseDTO contabilizarVotacao(@PathVariable Long id,
                                                            @RequestParam(name = "exibir_detalhamento_sessoes", required = false)
                                                            boolean exibirDetalhamentoSessoes) {
        return pautaService.contabilizarVotacao(id, exibirDetalhamentoSessoes);
    }
}
