package rest.assured.votacao.model.Pauta;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import rest.assured.votacao.enums.Categoria;

@Builder
@Getter
@Setter
@AllArgsConstructor
public class PautaRequest {
    String titulo;
    String descricao;
    Categoria categoria;
    Boolean ativaSessao;
    Integer tempoSessao;
}
