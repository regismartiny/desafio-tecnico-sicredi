package com.example.desafiotecnicosicredi.endpoint;

import static com.example.desafiotecnicosicredi.constants.I18Constants.CAMPOS_INVALIDOS;
import static com.example.desafiotecnicosicredi.constants.I18Constants.PAUTA_NAO_ENCONTRADA;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.OK;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import com.example.desafiotecnicosicredi.dto.pauta.PautaRequestDTO;

import io.restassured.response.Response;

class PautaResourceTest extends ResourceTest {

    private static final Long ID_PAUTA_EXISTENTE = 100L;
    private static final Long ID_PAUTA_INEXISTENTE = 500L;
    public static final String ENDPOINT = "/pauta";
    public static final String PATH_NOME = "nome";
    public static final String PATH_DESCRICAO = "descricao";
    private static final String PATH_NOME_PAUTA = "nomePauta";
    private static final String PATH_CONTABILIZACAO_SESSOES = "contabilizacaoSessoes";
    private static final String PATH_TOTALIZACAO_VOTOS = "totalizacaoDeVotos";
    public static final String PARAM_TRUE = "true";
    public static final String PARAM_FALSE = "false";
    public static final String PARAM_EXIBIR_DETALHAMENTO_SESSOES = "exibir_detalhamento_sessoes";

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
        var pautaRequestDTO = criarPautaValida();
        pautaRequestDTO.setNome(null);

        Response response = postRequest(ENDPOINT, pautaRequestDTO);

        assertErrorStatusAndMessage(response, BAD_REQUEST, getLocalMessage(CAMPOS_INVALIDOS));
    }

    @Test
    void criarPauta_quandoDescricaoNaoInformada_deveRetornarErro() {
        var pautaRequestDTO = criarPautaValida();
        pautaRequestDTO.setDescricao(null);

        Response response = postRequest(ENDPOINT, pautaRequestDTO);

        assertErrorStatusAndMessage(response, BAD_REQUEST, getLocalMessage(CAMPOS_INVALIDOS));
    }

    @Test
    void contabilizarVotos_quandoIdPautaInexistenteInformado_deveRetornarErro() {
        Response response = getRequest(ENDPOINT + "/" + ID_PAUTA_INEXISTENTE + "/contabilizar");

        assertErrorStatusAndMessage(response, NOT_FOUND, getLocalMessage(PAUTA_NAO_ENCONTRADA));
    }

    @Test
    void contabilizarVotos_quandoIdPautaValidoInformado_deveRetornarContabilizacao() {
        Response response = getRequest(ENDPOINT + "/" + ID_PAUTA_EXISTENTE + "/contabilizar");

        assertEquals(OK.value(), response.statusCode());
        assertNotNull(response.jsonPath().getString(PATH_NOME_PAUTA));
        assertNotNull(response.jsonPath().getString(PATH_TOTALIZACAO_VOTOS));
    }

    @Test
    void contabilizarVotos_quandoIdPautaValidoEOpcaoDeExibirDetalhamentoSessoesFalseInformado_deveRetornarContabilizacao() {
        Map<String, Object> params = new HashMap<>();
        params.put(PARAM_EXIBIR_DETALHAMENTO_SESSOES, PARAM_FALSE);

        Response response = getRequestWithQueryParam(ENDPOINT + "/" + ID_PAUTA_EXISTENTE + "/contabilizar", params);

        assertEquals(OK.value(), response.statusCode());
        assertNotNull(response.jsonPath().getString(PATH_NOME_PAUTA));
        assertNotNull(response.jsonPath().getString(PATH_TOTALIZACAO_VOTOS));
    }

    @Test
    void contabilizarVotos_quandoIdPautaValidoEOpcaoDeExibirDetalhamentoSessoesTrueInformado_deveRetornarContabilizacao() {
        Map<String, Object> params = new HashMap<>();
        params.put(PARAM_EXIBIR_DETALHAMENTO_SESSOES, PARAM_TRUE);

        Response response = getRequestWithQueryParam(ENDPOINT + "/" + ID_PAUTA_EXISTENTE + "/contabilizar", params);

        assertEquals(OK.value(), response.statusCode());
        assertNotNull(response.jsonPath().getString(PATH_NOME_PAUTA));
        assertNotNull(response.jsonPath().getString(PATH_CONTABILIZACAO_SESSOES));
        assertNotNull(response.jsonPath().getString(PATH_TOTALIZACAO_VOTOS));
    }

    private PautaRequestDTO criarPautaValida() {
        return PautaRequestDTO.builder()
                .nome("Pauta Teste")
                .descricao("Descricao")
                .build();
    }
}