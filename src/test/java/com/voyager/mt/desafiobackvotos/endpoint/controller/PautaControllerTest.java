package com.voyager.mt.desafiobackvotos.endpoint.controller;

import com.voyager.mt.desafiobackvotos.endpoint.dto.PautaDTO;
import com.voyager.mt.desafiobackvotos.endpoint.dto.VotoDTO;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;

import static io.restassured.RestAssured.given;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class PautaControllerTest {

    @LocalServerPort
    private int port;

    @BeforeEach
    public void setUp() throws Exception {
        RestAssured.port = port;
    }

    @Test
    void deveriaSalvarPauta() {
        PautaDTO pautaDTO = PautaDTO.builder()
                .nome("Abertura para capital externo")
                .descricao("Abriando capital com a venda de ativos da empresa para pagamento de divida")
                .build();

        given()
                    .body(pautaDTO)
                    .contentType(ContentType.JSON)
                .when()
                    .post("/pauta")
                .then()
                    .statusCode(HttpStatus.OK.value());
    }

}
