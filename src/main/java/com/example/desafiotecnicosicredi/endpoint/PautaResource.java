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

import com.example.desafiotecnicosicredi.dto.pauta.PautaRequestDTO;
import com.example.desafiotecnicosicredi.dto.pauta.PautaResponseDTO;
import com.example.desafiotecnicosicredi.service.PautaService;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/v1/pautas")
public class PautaResource {

    @Autowired
    PautaService pautaService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Criar pauta")
    public ResponseEntity<PautaResponseDTO> criarPauta(@Valid @RequestBody PautaRequestDTO pauta) {
        return ResponseEntity.ofNullable(pautaService.criarPauta(pauta));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar pauta pelo id")
    public ResponseEntity<PautaResponseDTO> buscarPauta(@PathVariable Long id) {
        return ResponseEntity.ofNullable(pautaService.consultarPauta(id));
    }
}
