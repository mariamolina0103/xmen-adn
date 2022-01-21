package com.xmen.core.repositorio;

import com.xmen.core.modelo.EstadoDTO;
import org.springframework.stereotype.Service;

@Service
public class RepositorioEstadoImpl implements EstadoReposiorio {

    private ReposiorioEstado estadoRepositorio;

    public RepositorioEstadoImpl(ReposiorioEstado estadoRepositorio) {
        this.estadoRepositorio = estadoRepositorio;
    }

    @Override
    public EstadoDTO obtenerEstado() {
        Long cuentaHumanos = this.estadoRepositorio.countByEsMutante(false);
        Long cuentaMutantes = this.estadoRepositorio.countByEsMutante(true);
        double radio = cuentaHumanos != 0 ? (double) cuentaMutantes / cuentaHumanos : 0d;

        EstadoDTO estadoDTO = EstadoDTO.builder()
                .count_human_dna(cuentaHumanos!=null  ? Integer.parseInt(cuentaHumanos.toString()) : 0)
                .count_mutant_dna(cuentaMutantes!=null  ? Integer.parseInt(cuentaMutantes.toString()) : 0)
                .ratio(radio)
                .build();
        return estadoDTO;
    }
}
