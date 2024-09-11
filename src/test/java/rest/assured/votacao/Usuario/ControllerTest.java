package rest.assured.votacao.Usuario;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import rest.assured.votacao.fixture.Usuario.UsuarioFixture;
import rest.assured.votacao.model.Usuario.UsuarioRequest;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

public class ControllerTest {

    @BeforeEach
    public void setUp() {
        RestAssured.baseURI = "http://localhost:8080";
    }

    @Test
    public void devePermitirCriarUsuarioComSucesso() {
        UsuarioRequest usuarioValido = UsuarioFixture.criarUsuarioValido();

        given()
            .contentType(ContentType.JSON)
            .body(usuarioValido)
        .when()
            .post("/usuario")
        .then()
            .statusCode(HttpStatus.CREATED.value()) 
            .body("nome", equalTo("Juliana Silva")) 
            .body("cpf", equalTo("617.457.200-57"));
    }

    @Test
    public void naoDevePermitirCriarUsuarioInvalido() {
        UsuarioRequest usuarioInvalido = UsuarioFixture.criarUsuarioInvalido();

        given()
            .contentType(ContentType.JSON)
            .body(usuarioInvalido)
        .when()
            .post("/usuario")
        .then()
            .statusCode(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    public void devePermitirVisualizarTodosUsuarios() {
        given()
            .contentType(ContentType.JSON)
        .when()
            .get("/usuario/visualizar")
        .then()
            .statusCode(HttpStatus.OK.value()) 
            .body("[0].nome", notNullValue())  
            .body("[0].cpf", notNullValue());   
    }

}
