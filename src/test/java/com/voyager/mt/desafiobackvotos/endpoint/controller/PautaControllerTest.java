package com.voyager.mt.desafiobackvotos.endpoint.controller;

import com.voyager.mt.desafiobackvotos.endpoint.dto.PautaDTO;
import com.voyager.mt.desafiobackvotos.endpoint.dto.VotoDTO;
import com.voyager.mt.desafiobackvotos.model.enume.VotoTipo;
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

    @Test
    void naoDeveriaSalvarPautaConflitoNomeDuplicado() {
        PautaDTO pautaDTO = PautaDTO.builder()
                .nome("Divisao do patrimonio aos socios")
                .descricao("divisao do patrimonio aos socios referente a janeiro")
                .build();

        given()
                .body(pautaDTO)
                .contentType(ContentType.JSON)
                .when()
                .post("/pauta")
                .then()
                .statusCode(HttpStatus.CONFLICT.value());
    }

    @Test
    void deveriaConseguirVotaSessaoAberta() {
        VotoDTO votoDTO = VotoDTO.builder()
                .associadoId(1L)
                .pautaId(1L)
                .tipo(VotoTipo.SIM)
                .dataEvento("12:30:12 27-06-2021")
                .build();

        given()
                .contentType(ContentType.JSON)
                .when()
                .put("/pauta/1/abertura")
                .then()
                .statusCode(HttpStatus.OK.value());

        given()
                .body(votoDTO)
                .contentType(ContentType.JSON)
                .when()
                .post("/pauta/voto")
                .then()
                .statusCode(HttpStatus.OK.value());
    }

    @Test
    void naoDeveriaConseguirVotaSessaoFechada() {
        VotoDTO votoDTO = VotoDTO.builder()
                .associadoId(1L)
                .pautaId(2L)
                .tipo(VotoTipo.SIM)
                .dataEvento("12:30:12 27-06-2021")
                .build();

        given()
                .body(votoDTO)
                .contentType(ContentType.JSON)
                .when()
                .post("/pauta/voto")
                .then()
                .statusCode(HttpStatus.CONFLICT.value());
    }

    @Test
    void deveriaAbrirSessaoPauta() {
        given()
                .contentType(ContentType.JSON)
                .when()
                .put("/pauta/1/abertura")
                .then()
                .statusCode(HttpStatus.OK.value());
    }

    @Test
    void deveriaObterResultadoPauta() {
        given()
                .contentType(ContentType.JSON)
                .when()
                .post("/pauta/1/resultado")
                .then()
                .statusCode(HttpStatus.OK.value());
    }

}
