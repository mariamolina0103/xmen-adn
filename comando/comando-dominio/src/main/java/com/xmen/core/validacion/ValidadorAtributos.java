package com.xmen.core.validacion;

import com.xmen.core.excepcion.ExcepcionValidacionDatos;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class ValidadorAtributos {



    private ValidadorAtributos() {
    }

    public static void validarObligatoriedad(String[] vector, String mensaje) {
        if (vector == null || vector.length == 0) {
            throw new ExcepcionValidacionDatos(mensaje);
        }
    }

    public static void validarLongitud(String valor, int longitud,String mensaje) {
        if (valor.trim().split("").length < longitud) {
            throw new ExcepcionValidacionDatos(mensaje);
        }
    }

    public static void validarPatron(String valor, String patron, String mensaje) {
        if (!valor.matches(patron)) {
            throw new ExcepcionValidacionDatos(mensaje);
        }
    }

    public static void validarLongitudDeTodosLosItems(String[] vector, String mensaje) {
        int longitudSecuencia = vector[0].length();
        for (String secuencia : vector) {
            if (secuencia.length() != longitudSecuencia) {
                throw new ExcepcionValidacionDatos(mensaje);
            }
        }
    }

    public static void validarItemsRepetidos(String[] adn, String mensaje) {
        Set<String> adnSet = new HashSet<>();
        Arrays.stream(adn).forEach(secuencia ->{
            if (!adnSet.add(secuencia)) {
                throw new ExcepcionValidacionDatos(mensaje);
            }
        });
    }
}
