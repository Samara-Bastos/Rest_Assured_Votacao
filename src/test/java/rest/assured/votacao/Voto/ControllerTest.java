package rest.assured.votacao.Voto;

import io.restassured.RestAssured;
import io.restassured.parsing.Parser;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import rest.assured.votacao.fixture.Voto.VotoFixture;
import rest.assured.votacao.model.Voto.VotoRequest;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class ControllerTest {
    
    @BeforeEach
    public void setUp() {
        RestAssured.baseURI = "http://localhost:8080"; 
        RestAssured.registerParser("text/plain", Parser.TEXT);
    }

    @Test
    public void deveRegistrarVotoComSucesso() {
        Long sessaoId = 1L;
        VotoRequest votoValido = VotoFixture.criarVotoValido();
        
        given()
            .contentType(ContentType.JSON)
            .body(votoValido)
        .when()
            .post("/voto/" + sessaoId)
        .then()
            .statusCode(HttpStatus.CREATED.value()) 
            .body("tipo", equalTo("SIM")) 
            .body("usuario.cpf", equalTo("617.457.200-57"));  
    }

    @Test
    public void naoDeveRegistrarVotoInvalido() {
        Long sessaoId = 1L; 
        VotoRequest votoInvalido = VotoFixture.criarVotoInvalido();

        given()
            .contentType(ContentType.JSON)
            .body(votoInvalido)
        .when()
            .post("/voto/" + sessaoId)
        .then()
            .statusCode(HttpStatus.BAD_REQUEST.value());  
    }

    @Test
    public void naoDeveRegistrarVotoComSessaoInativa() {
        Long sessaoId = 10L; 
        VotoRequest votoValido = VotoFixture.criarVotoValido();

        given()
            .contentType(ContentType.JSON)
            .body(votoValido)
        .when()
            .post("/voto/" + sessaoId)
        .then()
            .statusCode(HttpStatus.NOT_FOUND.value())  
            .body(equalTo("Não foi possivel encontrar essa sessão de votação")); 
    }

    @Test
    public void naoDeveRegistrarVotoSeUsuarioJaVotou() {
        Long sessaoId = 1L;
        VotoRequest votoValido = VotoFixture.criarVotoValido();

        given()
            .contentType(ContentType.JSON)
            .body(votoValido)
        .when()
            .post("/voto/" + sessaoId) 
        .then()
            .statusCode(HttpStatus.CONFLICT.value())  
            .body(equalTo("Esse usuário ja votou nessa pauta"));
    }

}
