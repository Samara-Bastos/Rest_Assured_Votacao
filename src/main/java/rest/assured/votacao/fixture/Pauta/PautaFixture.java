package rest.assured.votacao.fixture.Pauta;

import rest.assured.votacao.enums.Categoria;
import rest.assured.votacao.model.Pauta.PautaRequest;

public class PautaFixture {
    public static PautaRequest criarPautaValida() {
        return PautaRequest.builder()
            .titulo("Java é uma linguagem de programação?")
            .descricao("Você considera o Java uma linguagem de programação ou acredita que ele se encaixa em outra categoria de tecnologia?")
            .categoria(Categoria.TECNOLOGIA)
            .ativaSessao(true)
            .tempoSessao(10)
            .build();
    }

    public static PautaRequest criarPautaInvalida() {
        return PautaRequest.builder()
            .titulo("")
            .descricao("")
            .categoria(null)
            .ativaSessao(null)
            .tempoSessao(0)
            .build();
    }
}
