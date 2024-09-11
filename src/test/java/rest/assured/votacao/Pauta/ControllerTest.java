package rest.assured.votacao.Pauta;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import rest.assured.votacao.fixture.Pauta.PautaFixture;
import rest.assured.votacao.model.Pauta.PautaRequest;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.equalTo;

public class ControllerTest {
    
    @BeforeEach
    public void setUp() {
        RestAssured.baseURI = "http://localhost:8080";
    }

    @Test
    public void permiteCriarUmaPautaComSucesso() {
        PautaRequest pautaValida = PautaFixture.criarPautaValida();

        given()
            .contentType(ContentType.JSON)
            .body(pautaValida)
            .when()
            .post("/pauta")
            .then()
            .statusCode(HttpStatus.CREATED.value())
            .body("titulo", equalTo("Java é uma linguagem de programação?"));
    }

    @Test
    public void naoPermiteCriarUmaPautaInvalida() {
        PautaRequest pautaInvalida = PautaFixture.criarPautaInvalida();

        given()
            .contentType(ContentType.JSON)
            .body(pautaInvalida)
            .when()
            .post("/pauta")
            .then()
            .statusCode(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    public void permiteVisualizarTodasAsPautas() {
        given()
            .contentType(ContentType.JSON)
        .when()
            .get("/pauta/visualizar")
        .then()
            .statusCode(200)
            .body("[0].id", notNullValue())
            .body("[0].titulo", notNullValue());
    }

    @Test
    public void permiteVisualizarPautaPorId() {
        Long pautaId = 1L;

        given()
            .contentType(ContentType.JSON)
        .when()
            .get("/pauta/visualizar/" + pautaId)
        .then()
            .statusCode(200)
            .body("id", equalTo(pautaId.intValue()))
            .body("titulo", notNullValue());
    }

}
