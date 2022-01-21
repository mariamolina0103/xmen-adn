package com.xmen.core.modelo;

import com.xmen.core.validacion.ValidadorAtributos;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Arrays;

@Getter
@Setter
@NoArgsConstructor
public class MutanteDTO {

    public static final int LONGITUD_MINIMA = 4;
    private static final String ADN_OBLIGATORIO = "El ADN es obligatorio por lo tanto no debe ser nulo o vac\u00EDo";
    private static final String SECUENCIAS_DUPLICADAS = "El ADN no puede contener secuencias duplicadas";
    private static final String LA_SECUENCIA_DEBE_TENER_LONGITUD_MINIMA = String.format("Las secuencias ingresadas del ADN deben contener m\u00EDnimo %d caracteres", LONGITUD_MINIMA);
    private static final String TODOS_LAS_SECUENCIAS_DEBEN_TENER_LA_MISMA_LONGITUD = "Todas las secuencias ingresadas del ADN deben tener la misma longitud";
    private static final String PATRON = "^[ATCG]*$";
    private static final String DEBE_CUMPLIR_CON_FORMATO = "Las secuencias ingresadas del ADN solo pueden contener las letras A, T, C y G";

    private String id;
    private String[] adn;
    private boolean esMutant;

    public MutanteDTO(String[] adn) {
        ValidadorAtributos.validarObligatoriedad(adn,ADN_OBLIGATORIO);
        ValidadorAtributos.validarItemsRepetidos(adn,SECUENCIAS_DUPLICADAS);
        ValidadorAtributos.validarLongitudDeTodosLosItems(adn,TODOS_LAS_SECUENCIAS_DEBEN_TENER_LA_MISMA_LONGITUD);
        Arrays.stream(adn).forEach(secuencia -> {
            ValidadorAtributos.validarLongitud(secuencia,LONGITUD_MINIMA,LA_SECUENCIA_DEBE_TENER_LONGITUD_MINIMA);
            ValidadorAtributos.validarPatron(secuencia,PATRON,DEBE_CUMPLIR_CON_FORMATO);
        });
        this.adn = adn;
    }
}
