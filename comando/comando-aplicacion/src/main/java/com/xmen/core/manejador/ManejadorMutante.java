package com.xmen.core.manejador;

import com.xmen.core.comando.ComandoMutante;
import com.xmen.core.excepcion.ExcepcionNoMutante;
import com.xmen.core.fabrica.FabricaMutante;
import com.xmen.core.service.ServicioVerificarMutante;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional(noRollbackFor = {ExcepcionNoMutante.class})
public class ManejadorMutante {
    private final FabricaMutante fabricaMutante;
    private final ServicioVerificarMutante servicioVerificarMutante;

    public ManejadorMutante(FabricaMutante fabricaMutante, ServicioVerificarMutante servicioVerificarMutante) {
        this.fabricaMutante = fabricaMutante;
        this.servicioVerificarMutante = servicioVerificarMutante;
    }

    public Boolean ejecutar(ComandoMutante mutantCommand) {
        return servicioVerificarMutante.verificar(this.fabricaMutante.crear(mutantCommand));
    }
}
