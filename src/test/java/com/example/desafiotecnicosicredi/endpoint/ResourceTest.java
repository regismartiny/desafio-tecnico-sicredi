package com.example.desafiotecnicosicredi.endpoint;

import static io.restassured.RestAssured.given;

import java.util.Map;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.example.desafiotecnicosicredi.DesafioTecnicoSicrediApplication;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

@ExtendWith(SpringExtension.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT,
        classes = DesafioTecnicoSicrediApplication.class)
@TestPropertySource(
        locations = "classpath:application-integrationtest.properties")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ResourceTest {

    public static final String HEADER_CONTENT_TYPE = "Content-type";
    public static final String CONTENT_TYPE_JSON = "application/json";

    @BeforeAll
    public static void setup() {
        RestAssured.baseURI = "http://localhost:8080/v1";
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

    private static String objectToJson(Object body) {
        try {
            return new ObjectMapper().writeValueAsString(body);
        } catch (Exception ignored) {}
        return "";
    }
}
