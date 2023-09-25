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

import com.example.desafiotecnicosicredi.dto.voto.VotoRequestDTO;
import com.example.desafiotecnicosicredi.dto.voto.VotoResponseDTO;
import com.example.desafiotecnicosicredi.service.VotoService;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/v1/voto")
public class VotoResource {

    @Autowired
    VotoService votoService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Registrar voto")
    public VotoResponseDTO registrarVoto(@Valid @RequestBody VotoRequestDTO voto) {
        return votoService.registrarVoto(voto);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar voto pelo id")
    public VotoResponseDTO buscarVoto(@PathVariable Long id) {
        return votoService.consultarVoto(id);
    }
}
