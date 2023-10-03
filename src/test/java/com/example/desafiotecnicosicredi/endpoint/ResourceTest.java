package com.example.desafiotecnicosicredi.endpoint;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.example.desafiotecnicosicredi.DesafioTecnicoSicrediApplication;
import com.example.desafiotecnicosicredi.configuration.CustomMessageSource;
import com.example.desafiotecnicosicredi.constants.I18Constants;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

@ExtendWith(SpringExtension.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        classes = DesafioTecnicoSicrediApplication.class)
@TestPropertySource(locations = "classpath:application-integrationtest.properties")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ResourceTest {
    private final static String BASE_URI = "http://localhost/v1";
    public static final String HEADER_CONTENT_TYPE = "Content-type";
    public static final String CONTENT_TYPE_JSON = "application/json";
    public static final String PATH_STATUS = "status";
    public static final String PATH_MESSAGE = "message";
    public static final String PATH_ID = "id";

    @LocalServerPort
    private int port;

    @Autowired
    private CustomMessageSource messageSource;

    protected String getLocalMessage(I18Constants msg) {
        return messageSource.getMessage(msg.getKey());
    }

    @BeforeEach
    public void setup() {
        RestAssured.baseURI = BASE_URI;
        RestAssured.port = port;
    }

    public Response postRequest(String url, Object body) {
        String requestBody = objectToJson(body);
        return given()
                .header(HEADER_CONTENT_TYPE, CONTENT_TYPE_JSON)
                .and()
                .body(requestBody)
                .when()
                .post(url)
                .then()
                .extract().response();
    }

    public Response getRequestWithQueryParam(String url, Map<String, Object> params) {
        return given()
                .contentType(ContentType.JSON)
                .params(params)
                .when()
                .get(url)
                .then()
                .extract().response();
    }

    public Response getRequest(String url) {
        return given()
                .contentType(ContentType.JSON)
                .when()
                .get(url)
                .then()
                .extract().response();
    }

    public void assertErrorStatusAndMessage(Response response, HttpStatus status, String message) {
        assertEquals(status.value(), response.statusCode());
        assertEquals(status.value(), response.jsonPath().getInt(PATH_STATUS));
        assertEquals(message, response.jsonPath().getString(PATH_MESSAGE));
    }

    private static String objectToJson(Object body) {
        try {
            return new ObjectMapper().writeValueAsString(body);
        } catch (Exception ignored) {}
        return "{}";
    }
}
