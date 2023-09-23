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

import com.example.desafiotecnicosicredi.dto.pauta.consultar.PautaConsultarDTO;
import com.example.desafiotecnicosicredi.dto.pauta.criar.PautaCriarDTO;
import com.example.desafiotecnicosicredi.service.PautaService;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/pautas")
public class PautaResource {

    @Autowired
    PautaService pautaService;

    @GetMapping("/{id}")
    @Operation(summary = "Buscar pauta pelo id")
    public ResponseEntity<PautaConsultarDTO> buscarPauta(@PathVariable Long id) {
        return ResponseEntity.ofNullable(pautaService.consultarPauta(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void criarPauta(@Valid @RequestBody PautaCriarDTO pauta) {
        pautaService.criarPauta(pauta);
    }
}
