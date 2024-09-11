package rest.assured.votacao.fixture.Usuario;

import rest.assured.votacao.model.Usuario.UsuarioRequest;

public class UsuarioFixture {
    public static UsuarioRequest criarUsuarioValido() {
        return UsuarioRequest.builder()
            .nome("Juliana Silva")
            .cpf("617.457.200-57")
            .build();
    }

    public static UsuarioRequest criarUsuarioInvalido() {
        return UsuarioRequest.builder()
            .nome("")
            .cpf("123456789")
            .build();
    }
}
