package com.example.desafiotecnicosicredi.endpoint;

import static com.example.desafiotecnicosicredi.constants.I18Constants.CAMPOS_INVALIDOS;
import static com.example.desafiotecnicosicredi.constants.I18Constants.PAUTA_NAO_ENCONTRADA;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.OK;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import com.example.desafiotecnicosicredi.dto.pauta.PautaRequestDTO;

import io.restassured.response.Response;

class PautaResourceTest extends ResourceTest {

    private static final Long ID_PAUTA_INEXISTENTE = 500L;
    public static final String ENDPOINT = "/pauta";
    public static final String PATH_NOME = "nome";
    public static final String PATH_DESCRICAO = "descricao";

    @Test
    @Order(1)
    void criarPauta_quandoDadosValidosInformados_deveCriarERetornarPauta() {
        var pautaRequestDTO = criarPautaValida();

        Response response = postRequest(ENDPOINT, pautaRequestDTO);

        assertEquals(CREATED.value(), response.statusCode());
        assertEquals(pautaRequestDTO.getNome(), response.jsonPath().getString(PATH_NOME));
        assertEquals(pautaRequestDTO.getDescricao(), response.jsonPath().getString(PATH_DESCRICAO));
    }

    @Test
    @Order(2)
    void buscarPauta_quandoIdPautaExistenteInformado_deveRetornarPauta() {
        Response response = getRequest(ENDPOINT + "/1");

        assertEquals(OK.value(), response.statusCode());
        assertEquals(1L, response.jsonPath().getLong(PATH_ID));
        assertEquals("Pauta Teste", response.jsonPath().getString(PATH_NOME));
        assertEquals("Descricao", response.jsonPath().getString(PATH_DESCRICAO));
    }

    @Test
    void buscarPauta_quandoIdPautaInexistenteInformado_deveRetornarErro() {
        Response response = getRequest(ENDPOINT + "/" + ID_PAUTA_INEXISTENTE);

        assertErrorStatusAndMessage(response, NOT_FOUND, getLocalMessage(PAUTA_NAO_ENCONTRADA));
    }

    @Test
    void criarPauta_quandoNomeNaoInformado_deveRetornarErro() {
        var pautaRequestDTO = PautaRequestDTO.builder().descricao("DESCRICAO").build();

        Response response = postRequest(ENDPOINT, pautaRequestDTO);

        assertErrorStatusAndMessage(response, BAD_REQUEST, getLocalMessage(CAMPOS_INVALIDOS));
    }

    @Test
    void criarPauta_quandoDescricaoNaoInformada_deveRetornarErro() {
        var pautaRequestDTO = PautaRequestDTO.builder().nome("PAUTA TESTE").build();

        Response response = postRequest(ENDPOINT, pautaRequestDTO);

        assertErrorStatusAndMessage(response, BAD_REQUEST, getLocalMessage(CAMPOS_INVALIDOS));
    }

    private PautaRequestDTO criarPautaValida() {
        return PautaRequestDTO.builder()
                .nome("Pauta Teste")
                .descricao("Descricao")
                .build();
    }
}