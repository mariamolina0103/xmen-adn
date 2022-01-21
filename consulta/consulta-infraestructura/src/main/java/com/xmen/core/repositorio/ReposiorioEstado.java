package com.xmen.core.repositorio;

import com.xmen.core.entidad.MutanteEntidad;
import org.socialsignin.spring.data.dynamodb.repository.EnableScanCount;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
@EnableScanCount
public interface ReposiorioEstado extends CrudRepository<MutanteEntidad,String> {

    Long countByEsMutante(boolean esMutante);
}
