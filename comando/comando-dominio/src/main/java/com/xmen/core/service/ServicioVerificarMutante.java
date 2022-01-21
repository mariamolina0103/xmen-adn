package com.xmen.core.service;

import com.xmen.core.excepcion.ExcepcionMutante;
import com.xmen.core.excepcion.ExcepcionNoMutante;
import com.xmen.core.modelo.Mutante;
import com.xmen.core.modelo.MutanteDTO;
import com.xmen.core.repositorio.RepositorioMutante;

import java.util.ArrayList;
import java.util.Arrays;

import static java.lang.Math.max;
import static java.lang.Math.min;

public class ServicioVerificarMutante {

    private final RepositorioMutante mutantRepository;

    public ServicioVerificarMutante(RepositorioMutante mutantRepository) {
        this.mutantRepository = mutantRepository;
    }

    public Boolean verificar(MutanteDTO mutanteDTO) {
        this.validarExistenciaAdn(mutanteDTO.getAdn());
        mutanteDTO.setEsMutant(tieneAdnMutante(mutanteDTO.getAdn()));
        this.mutantRepository.guardar(Mutante.builder().esMutante(mutanteDTO.isEsMutant()).adn(Arrays.toString(mutanteDTO.getAdn())).build());
        if (mutanteDTO.isEsMutant()) {
            return true;
        } else {
            throw new ExcepcionNoMutante("El ADN enviado no pertene a un mutante");
        }
    }

    private void validarExistenciaAdn(String[] dna) {
        if (this.mutantRepository.existeADN(dna)) {
            throw new ExcepcionMutante("El ADN enviado ya fue verificado previamente");
        }
    }

    /**
     * Permite saber si unas secuencias de adn son mutantes
     *
     * @param adn vector con secuencias de adn
     * @return booleano para identificar si se es mutante o no
     */
    private Boolean tieneAdnMutante(String[] adn){
        return contarSecuenciasMutantes(adn) > 1;
    }

    /**
     * Cuenta las secuencias mutantes del adn
     *
     * @param adn vector con secuencias de adn
     * @return dato entero con numero de secuencias con mutacion
     */
    private Integer contarSecuenciasMutantes(String[] adn){
        String[][] matriz = crearMatriz(adn);
        return contarSecuenciasRepetidasHorizontales(adn)+
                contarSecuenciasRepetidasVerticales(matriz)+
                contarSecuenciasRepetidasDiagonalesIzquierda(matriz)+
                contarSecuenciasRepetidasDiagonalesDerecha(matriz);

    }

    /**
     * Cuenta las secuencias mutantes del adn horizontales
     *
     * @param adn vector con secuencias de adn
     * @return dato entero con numero de secuencias con mutacion
     */
    private int contarSecuenciasRepetidasHorizontales(String[] adn){
        return tieneCoincidenciasMutantes(adn) ? 1 : 0;
    }

    /**
     * Cuenta las secuencias mutantes del adn verticales
     *
     * @param matrizAdn matriz con secuencias de adn organizadas
     * @return dato entero con numero de secuencias con mutacion
     */
    private int contarSecuenciasRepetidasVerticales(String[][] matrizAdn){
        return tieneCoincidenciasMutantes(crearVectorConColumnas(matrizAdn)) ? 1 : 0;
    }

    /**
     * Cuenta las secuencias mutantes del adn organizado en la matriz diagonal izquierda
     *
     * @param matrizAdn  matriz con secuencias de adn organizadas
     * @return dato entero con numero de secuencias con mutacion
     */
    private int contarSecuenciasRepetidasDiagonalesIzquierda(String[][] matrizAdn){
        return tieneCoincidenciasMutantes(crearVectorConDiagonalIzquierda(matrizAdn)) ? 1 : 0;
    }

    /**
     * Cuenta las secuencias mutantes del adn organizado en la matriz diagonal derecha
     *
     * @param matrizAdn  matriz con secuencias de adn organizadas
     * @return dato entero con numero de secuencias con mutacion
     */
    private int contarSecuenciasRepetidasDiagonalesDerecha(String[][] matrizAdn){
        return tieneCoincidenciasMutantes(crearVectorConDiagonalDerecha(matrizAdn)) ? 1 : 0;
    }

    /**
     * Crea una matriz a partir un vector de adn
     *
     * @param adn vector con secuencias de adn
     * @return matriz organizada a partir del adn
     */
    private String[][] crearMatriz(String[] adn){
        String[][] matriz = new String[adn.length][adn.length];
        for (int row = 0; row < adn.length; row++) {
            String[] rowDna = adn[row].split("");
            for (int column = 0; column < adn.length; column++) {
                matriz[row][column] = rowDna[column];
            }
        }
        return matriz;
    }

    /**
     * Crea una matriz a partir un vector de adn
     *
     * @param matriz matriz original creada apartir del adn
     * @return vector con secuencias invertidas en columnas
     */
    private String[] crearVectorConColumnas(String[][] matriz){
        String[] vector = new String[matriz.length];
        for(int j = 0; j < matriz.length; j++) {
            String cadena="";
            for(int i = 0; i < matriz.length; i++) {
                cadena = cadena + matriz[i][j];
            }
            vector[j] = cadena;
        }
        return vector;
    }

    /**
     * Crea una matriz a partir un vector de adn
     *
     * @param matriz matriz original creada apartir del adn
     * @return matriz organizada con secuencias de los resultados diagonales de la matriz original de izquierda a derecha
     */

    private String[] crearVectorConDiagonalIzquierda(String[][] matriz){
        String[] arrayIzquierda = new String[matriz.length * 2];
        int posAux = 0;
        for (int i = 1 - matriz.length; i < matriz.length; i++){
            String cadena = "";
            for (int x = -min(0, i), y = max(0, i); x < matriz.length && y < matriz.length; x++, y++){
                cadena = cadena + matriz[y][x];
            }
            arrayIzquierda[posAux] = cadena;
            posAux++;
        }
        return arrayIzquierda;
    }

    /**
     * Crea una matriz a partir un vector de adn
     *
     * @param matriz matriz original creada apartir del adn
     * @return matriz organizada con secuencias de los resultados diagonales de la matriz original de derecha a izquierda
     */
    private String[] crearVectorConDiagonalDerecha(String[][] matriz){
        ArrayList<ArrayList<String>> ans = new ArrayList<>(matriz.length+matriz.length-1);
        for(int i = 0; i < matriz.length + matriz.length - 1; i++) {ans.add(new ArrayList<>()); }
        for (int i = 0; i < matriz.length; i++){for (int j = 0; j < matriz.length; j++) {(ans.get(i+j)).add(matriz[i][j]);}}

        String[] arrayDerecha = new String[matriz.length * 2];
        int posAux2 = 0;
        for (int i = 0; i < ans.size(); i++){
            String cadena="";
            for (int j = ans.get(i).size() - 1; j >= 0; j--){
                cadena = cadena + ans.get(i).get(j);
            }
            arrayDerecha[posAux2] = cadena;
            posAux2++;
        }
        return arrayDerecha;
    }

    /**
     * Valida si en un vector de secuencia se cumple el patron de 4 letras permitidas continuamente
     *
     * @param adn vector organizado
     * @return boolean para saber si el adn enviado contiene secuencias mutantes
     */
    private Boolean tieneCoincidenciasMutantes(String[] adn){
        return Arrays.stream(adn).anyMatch(secuencia -> secuencia !=null && secuencia.matches("(?i).*([A-C-G-T])\\1{3}.*"));
    }

}