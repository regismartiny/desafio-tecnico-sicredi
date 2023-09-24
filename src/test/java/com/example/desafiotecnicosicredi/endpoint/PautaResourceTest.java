package com.example.desafiotecnicosicredi.endpoint;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import com.example.desafiotecnicosicredi.dto.pauta.PautaRequestDTO;

import io.restassured.response.Response;

class PautaResourceTest extends ResourceTest {

    @Test
    @Order(1)
    void criarPauta_quandoDadosValidosInformados_deveCriarERetornarPauta() {
        PautaRequestDTO pautaRequestDTO = criarPautaTest();

        Response response = postRequest("/pauta", pautaRequestDTO);

        assertEquals(201, response.statusCode());
        assertEquals(pautaRequestDTO.getNome(), response.jsonPath().getString("nome"));
        assertEquals(pautaRequestDTO.getDescricao(), response.jsonPath().getString("descricao"));
    }

    @Test
    @Order(2)
    void buscarPauta_quandoIdPautaExistenteInformado_deveRetornarPauta() {
        Response response = getRequest("/pauta/1");

        assertEquals(200, response.statusCode());
        assertEquals(1L, response.jsonPath().getLong("id"));
        assertEquals("Pauta Teste", response.jsonPath().getString("nome"));
        assertEquals("Descricao", response.jsonPath().getString("descricao"));
    }

    @Test
    void buscarPauta_quandoIdPautaInexistenteInformado_deveRetornarErro() {
        Response response = getRequest("/pauta/100");

        assertEquals(404, response.statusCode());
        assertEquals("404", response.jsonPath().getString("status"));
        assertEquals("Registro não encontrado", response.jsonPath().getString("message"));
    }

    @Test
    void criarPauta_quandoNomeNaoInformado_deveRetornarErro() {
        PautaRequestDTO pautaRequestDTO = PautaRequestDTO.builder().descricao("DESCRICAO").build();

        Response response = postRequest("/pauta", pautaRequestDTO);

        assertEquals(400, response.statusCode());
        assertEquals("400", response.jsonPath().getString("status"));
        assertEquals("Há campos inválidos na requisição", response.jsonPath().getString("message"));
    }

    @Test
    void criarPauta_quandoDescricaoNaoInformada_deveRetornarErro() {
        PautaRequestDTO pautaRequestDTO = PautaRequestDTO.builder().nome("PAUTA TESTE").build();

        Response response = postRequest("/pauta", pautaRequestDTO);

        assertEquals(400, response.statusCode());
        assertEquals("400", response.jsonPath().getString("status"));
        assertEquals("Há campos inválidos na requisição", response.jsonPath().getString("message"));
    }

    private PautaRequestDTO criarPautaTest() {
        return PautaRequestDTO.builder()
                .nome("Pauta Teste")
                .descricao("Descricao")
                .build();
    }
}