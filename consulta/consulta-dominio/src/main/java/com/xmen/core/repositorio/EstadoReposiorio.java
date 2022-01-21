package com.xmen.core.repositorio;

import com.xmen.core.modelo.EstadoDTO;

public interface EstadoReposiorio {
    /**
     * Permite consultar el estado de las verificaciones de ADN
     *
     * @return estado
     */
    EstadoDTO obtenerEstado();
}
