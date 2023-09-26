package com.example.desafiotecnicosicredi.endpoint;

import static com.example.desafiotecnicosicredi.constants.I18Constants.CAMPOS_INVALIDOS;
import static com.example.desafiotecnicosicredi.constants.I18Constants.PAUTA_NAO_ENCONTRADA;
import static com.example.desafiotecnicosicredi.constants.I18Constants.SESSAO_VOTACAO_NAO_ENCONTRADA;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.OK;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import com.example.desafiotecnicosicredi.dto.sessaovotacao.SessaoVotacaoRequestDTO;

import io.restassured.response.Response;

class SessaoVotacaoResourceTest extends ResourceTest {

    private static final Long ID_PAUTA_EXISTENTE = 100L;
    private static final Long ID_PAUTA_INEXISTENTE = 500L;
    private static final Long ID_SESSAO_VOTACAO_EXISTENTE = 100L;
    private static final Long ID_SESSAO_VOTACAO_INEXISTENTE = 500L;
    public static final String PATH_ID_PAUTA = "idPauta";
    public static final String PATH_DATA_INICIO = "dataInicio";
    public static final String PATH_DATA_FIM_VALIDADE = "dataFimValidade";
    public static final String ENDPOINT = "/sessao-votacao";
    private static final String PATH_NOME_PAUTA = "nomePauta";
    private static final String PATH_SITUACAO_SESSAO = "contabilizacaoSessao.situacaoSessao";
    private static final String PATH_VOTOS_CONTABILIZADOS = "contabilizacaoSessao.votosContabilizados";

    @Test
    @Order(1)
    void criarSessaoVotacao_quandoDadosValidosInformados_deveCriarERetornarSessaoVotacao() {
        var sessaoVotacaoRequestDTO = criarSessaoVotacaoValida();

        Response response = postRequest(ENDPOINT, sessaoVotacaoRequestDTO);

        assertEquals(CREATED.value(), response.statusCode());
        assertEquals(sessaoVotacaoRequestDTO.getIdPauta(), response.jsonPath().getLong(PATH_ID_PAUTA));
        assertNotNull(response.jsonPath().getString(PATH_DATA_INICIO));
        assertNotNull(response.jsonPath().getString(PATH_DATA_FIM_VALIDADE));
    }

    @Test
    @Order(2)
    void buscarSessaoVotacao_quandoIdSessaoValidoInformado_deveRetornarSessaoVotacao() {
        Response response = getRequest(ENDPOINT + "/1");

        assertEquals(OK.value(), response.statusCode());
        assertEquals(1L, response.jsonPath().getLong(PATH_ID));
        assertEquals(ID_PAUTA_EXISTENTE, response.jsonPath().getLong(PATH_ID_PAUTA));
    }

    @Test
    void criarSessaoVotacao_quandoIdPautaNaoInformado_deveRetornarErro() {
        var sessaoVotacaoRequestDTO = SessaoVotacaoRequestDTO.builder().build();

        Response response = postRequest(ENDPOINT, sessaoVotacaoRequestDTO);

        assertErrorStatusAndMessage(response, BAD_REQUEST, getLocalMessage(CAMPOS_INVALIDOS));
    }

    @Test
    void criarSessaoVotacao_quandoIdPautaInexistenteInformado_deveRetornarErro() {
        var sessaoVotacaoRequestDTO = SessaoVotacaoRequestDTO.builder().idPauta(ID_PAUTA_INEXISTENTE).build();

        Response response = postRequest(ENDPOINT, sessaoVotacaoRequestDTO);

        assertErrorStatusAndMessage(response, NOT_FOUND, getLocalMessage(PAUTA_NAO_ENCONTRADA));
    }

    @Test
    void buscarSessaoVotacao_quandoIdSessaoVotacaoInexistenteInformado_deveRetornarErro() {
        Response response = getRequest(ENDPOINT + "/" + ID_PAUTA_INEXISTENTE);

        assertErrorStatusAndMessage(response, NOT_FOUND, getLocalMessage(SESSAO_VOTACAO_NAO_ENCONTRADA));
    }

    @Test
    void contabilizarSessaoVotacao_quandoIdSessaoVotacaoInexistenteInformado_deveRetornarErro() {
        Response response = getRequest(ENDPOINT + "/" + ID_SESSAO_VOTACAO_INEXISTENTE + "/contabilizar");

        assertErrorStatusAndMessage(response, NOT_FOUND, getLocalMessage(SESSAO_VOTACAO_NAO_ENCONTRADA));
    }

    @Test
    void contabilizarSessaoVotacao_quandoIdSessaoValidoInformado_deveRetornarContabilizacao() {
        Response response = getRequest(ENDPOINT + "/" + ID_SESSAO_VOTACAO_EXISTENTE + "/contabilizar");

        assertEquals(OK.value(), response.statusCode());
        assertNotNull(response.jsonPath().getString(PATH_NOME_PAUTA));
        assertNotNull(response.jsonPath().getString(PATH_SITUACAO_SESSAO));
        assertNotNull(response.jsonPath().getString(PATH_VOTOS_CONTABILIZADOS));
    }

    private SessaoVotacaoRequestDTO criarSessaoVotacaoValida() {
        return SessaoVotacaoRequestDTO.builder()
                .idPauta(ID_PAUTA_EXISTENTE)
                .build();

    }
}