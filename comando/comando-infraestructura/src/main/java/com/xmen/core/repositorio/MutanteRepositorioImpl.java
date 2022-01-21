package com.xmen.core.repositorio;

import com.xmen.core.modelo.Mutante;
import com.xmen.core.modelo.MutanteEntidad;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.Optional;

@Repository
public class MutanteRepositorioImpl extends MapperImpl<Mutante, MutanteEntidad> implements RepositorioMutante {

    private MutanteRepository mutanteRepository;

    public MutanteRepositorioImpl(ModelMapper modelMapper, MutanteRepository mutanteRepository) {
        super(modelMapper, Mutante.class, MutanteEntidad.class);
        this.mutanteRepository = mutanteRepository;
    }

    @Override
    public void guardar(Mutante mutante) {
        mutanteRepository.save(mapToEntity(mutante));
    }

    @Override
    public boolean existeADN(String[] adn) {
        Optional<MutanteEntidad> mutanteEntidad = mutanteRepository.findByAdn(Arrays.toString(adn));
        return mutanteEntidad!=null && mutanteEntidad.isPresent();
    }
}
