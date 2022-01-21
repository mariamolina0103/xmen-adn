package com.xmen.core.repositorio;

import com.xmen.core.modelo.MutanteEntidad;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

@EnableScan
public interface MutanteRepository extends CrudRepository<MutanteEntidad,String> {
    Optional<MutanteEntidad> findByAdn(String dna);
}
