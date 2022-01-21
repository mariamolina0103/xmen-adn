package com.xmen.core.excepcion;

public class ExcepcionNoMutante extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public ExcepcionNoMutante(String mensaje) {
        super(mensaje);
    }
}
