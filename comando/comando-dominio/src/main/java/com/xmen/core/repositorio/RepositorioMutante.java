package com.xmen.core.repositorio;

import com.xmen.core.modelo.Mutante;

public interface RepositorioMutante {

    /**
     * Guarda la información de los ADN
     *
     * @param mutante objeto con la informacion el ADN y el atributo para identificar si es o no mutante
     */
    void guardar(Mutante mutante);

    /**
     * Muestra si el ADN ya esta registrado en la base de datos
     *
     * @param adn ADN a buscar
     * @return
     */
    boolean existeADN(String[] adn);
}
