package rest.assured.votacao.fixture.Voto;

import rest.assured.votacao.enums.TipoVoto;
import rest.assured.votacao.model.Voto.VotoRequest;

public class VotoFixture {
    public static VotoRequest criarVotoValido() {
        return VotoRequest.builder()
            .tipo(TipoVoto.SIM) 
            .cpf("617.457.200-57") 
            .build();
    }

    public static VotoRequest criarVotoInvalido() {
        return VotoRequest.builder()
            .tipo(null) 
            .cpf("")
            .build();
    }
}
