package com.xmen.core.excepcion;

public class ExcepcionValidacionDatos extends RuntimeException{

    private static final long serialVersionUID = 1L;

    public ExcepcionValidacionDatos(String mensaje) {
        super(mensaje);
    }

}
