package com.xmen.core.modelo;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Mutante {

    private String id;
    private String adn;
    private boolean esMutante;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAdn() {
        return adn;
    }

    public void setAdn(String adn) {
        this.adn = adn;
    }

    public boolean getEsMutante() {
        return esMutante;
    }

    public void setEsMutante(boolean esMutante) {
        this.esMutante = esMutante;
    }
}
