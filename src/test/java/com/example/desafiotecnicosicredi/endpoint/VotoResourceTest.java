package com.example.desafiotecnicosicredi.endpoint;

import static com.example.desafiotecnicosicredi.constants.I18Constants.ASSOCIADO_JA_VOTOU;
import static com.example.desafiotecnicosicredi.constants.I18Constants.CAMPOS_INVALIDOS;
import static com.example.desafiotecnicosicredi.constants.I18Constants.SESSAO_ENCERRADA;
import static com.example.desafiotecnicosicredi.constants.I18Constants.SESSAO_NAO_INICIOU;
import static com.example.desafiotecnicosicredi.constants.I18Constants.VOTO_NAO_ENCONTRADO;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.OK;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import com.example.desafiotecnicosicredi.dto.voto.VotoRequestDTO;
import com.example.desafiotecnicosicredi.enums.OpcaoVoto;
import com.example.desafiotecnicosicredi.utils.CPFUtil;

import io.restassured.response.Response;

class VotoResourceTest extends ResourceTest {

    public static final String ENDPOINT = "/voto";
    private static final Long ID_VOTO_INEXISTENTE = 500L;
    private static final Long ID_SESSAO_VOTACAO_EXISTENTE = 100L;
    private static final Long ID_SESSAO_VOTACAO_NAO_INICIADA = 101L;
    private static final Long ID_SESSAO_VOTACAO_ENCERRADA = 102L;
    private static final String CPF_ASSOCIADO_VALIDO = CPFUtil.gerarCPF(false);
    private static final String PATH_CPF_ASSOCIADO = "cpfAssociado";
    private static final String PATH_OPCAO_VOTO = "opcaoVoto";
    private static final String PATH_ID_SESSAO = "idSessao";

    @Test
    @Order(1)
    void registrarVoto_quandoDadosValidosInformados_deveCriarERetornarSessaoVotacao() {
        var votoRequestDTO = criarVotoValido();

        Response response = postRequest(ENDPOINT, votoRequestDTO);

        assertEquals(CREATED.value(), response.statusCode());
        assertNotNull(response.jsonPath().getString(PATH_ID));
        assertEquals(votoRequestDTO.getCpfAssociado(), response.jsonPath().getString(PATH_CPF_ASSOCIADO));
        assertEquals(votoRequestDTO.getOpcao().name(), response.jsonPath().getString(PATH_OPCAO_VOTO));
        assertEquals(votoRequestDTO.getIdSessao(), response.jsonPath().getLong(PATH_ID_SESSAO));
    }

    @Test
    @Order(2)
    void buscarVoto_quandoIdVotoValidoInformado_deveRetornarVoto() {
        Response response = getRequest(ENDPOINT + "/1");

        assertEquals(OK.value(), response.statusCode());
        assertEquals(1L, response.jsonPath().getLong(PATH_ID));
        assertNotNull(response.jsonPath().getString(PATH_CPF_ASSOCIADO));
        assertNotNull(response.jsonPath().getString(PATH_OPCAO_VOTO));
        assertEquals(ID_SESSAO_VOTACAO_EXISTENTE, response.jsonPath().getLong(PATH_ID_SESSAO));
    }

    @Test
    void registrarVoto_quandoIdSessaoNaoInformado_deveRetornarErro() {
        var votoRequestDTO = criarVotoValido();
        votoRequestDTO.setIdSessao(null);

        Response response = postRequest(ENDPOINT, votoRequestDTO);

        assertErrorStatusAndMessage(response, BAD_REQUEST, getLocalMessage(CAMPOS_INVALIDOS));
    }

    @Test
    void registrarVoto_quandoOpcaoNaoInformada_deveRetornarErro() {
        var votoRequestDTO = criarVotoValido();
        votoRequestDTO.setOpcao(null);

        Response response = postRequest(ENDPOINT, votoRequestDTO);

        assertErrorStatusAndMessage(response, BAD_REQUEST, getLocalMessage(CAMPOS_INVALIDOS));
    }

    @Test
    void registrarVoto_quandoCPFAssociadoNaoInformado_deveRetornarErro() {
        var votoRequestDTO = criarVotoValido();
        votoRequestDTO.setCpfAssociado(null);

        Response response = postRequest(ENDPOINT, votoRequestDTO);

        assertErrorStatusAndMessage(response, BAD_REQUEST, getLocalMessage(CAMPOS_INVALIDOS));
    }

    @Test
    void registrarVoto_quandoCPFAssociadoInvalidoInformado_deveRetornarErro() {
        var votoRequestDTO = criarVotoValido();
        votoRequestDTO.setCpfAssociado("123");

        Response response = postRequest(ENDPOINT, votoRequestDTO);

        assertErrorStatusAndMessage(response, BAD_REQUEST, getLocalMessage(CAMPOS_INVALIDOS));
    }

    @Test
    void registrarVoto_quandoIdSessaoNaoIniciadaInformado_deveRetornarErro() {
        var votoRequestDTO = criarVotoValido();
        votoRequestDTO.setIdSessao(ID_SESSAO_VOTACAO_NAO_INICIADA);

        Response response = postRequest(ENDPOINT, votoRequestDTO);

        assertErrorStatusAndMessage(response, BAD_REQUEST, getLocalMessage(SESSAO_NAO_INICIOU));
    }

    @Test
    void registrarVoto_quandoIdSessaoEncerradaInformado_deveRetornarErro() {
        var votoRequestDTO = criarVotoValido();
        votoRequestDTO.setIdSessao(ID_SESSAO_VOTACAO_ENCERRADA);

        Response response = postRequest(ENDPOINT, votoRequestDTO);

        assertErrorStatusAndMessage(response, BAD_REQUEST, getLocalMessage(SESSAO_ENCERRADA));
    }

    @Test
    void registrarVoto_quandoCPFAssociadoQueJaVotouInformado_deveRetornarErro() {
        var votoRequestDTO = criarVotoValido();

        Response response = postRequest(ENDPOINT, votoRequestDTO);

        assertErrorStatusAndMessage(response, BAD_REQUEST, getLocalMessage(ASSOCIADO_JA_VOTOU));
    }

    @Test
    void buscarVoto_quandoIdVotoNaoExistenteInformado_deveRetornarErro() {
        Response response = getRequest(ENDPOINT + "/" + ID_VOTO_INEXISTENTE);

        assertErrorStatusAndMessage(response, NOT_FOUND, getLocalMessage(VOTO_NAO_ENCONTRADO));
    }

    private VotoRequestDTO criarVotoValido() {
        return VotoRequestDTO.builder()
                .opcao(OpcaoVoto.SIM)
                .cpfAssociado(CPF_ASSOCIADO_VALIDO)
                .idSessao(ID_SESSAO_VOTACAO_EXISTENTE)
                .build();
    }
}