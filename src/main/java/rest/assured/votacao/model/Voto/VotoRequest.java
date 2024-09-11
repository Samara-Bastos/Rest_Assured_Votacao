package rest.assured.votacao.model.Voto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import rest.assured.votacao.enums.TipoVoto;

@Builder
@Getter
@Setter
@AllArgsConstructor
public class VotoRequest {
    TipoVoto tipo;
    String cpf;
}
