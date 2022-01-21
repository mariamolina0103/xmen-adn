package com.xmen.core.manejador;

import com.xmen.core.repositorio.EstadoReposiorio;
import com.xmen.core.modelo.EstadoDTO;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public class ManejadorBuscarEstado {

    private final EstadoReposiorio repositorioEstado;

    public ManejadorBuscarEstado(EstadoReposiorio repositorioEstado) {
        this.repositorioEstado = repositorioEstado;
    }

    public EstadoDTO ejecutar() {
        return repositorioEstado.obtenerEstado();
    }
}
