package com.xmen.core.excepcion;

public class ExcepcionMutante extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public ExcepcionMutante(String mensaje) {
        super(mensaje);
    }
}
