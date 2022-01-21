package com.xmen.core.fabrica;

import com.xmen.core.comando.ComandoMutante;
import com.xmen.core.modelo.MutanteDTO;
import org.springframework.stereotype.Component;

@Component
public class FabricaMutante {

    public MutanteDTO crear(ComandoMutante comandoMutante) {
        return new MutanteDTO(comandoMutante.getDna());
    }
}
