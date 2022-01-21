package com.xmen.core.configuracion;

import com.xmen.core.excepcion.ExcepcionMutante;
import com.xmen.core.excepcion.ExcepcionNoMutante;
import com.xmen.core.excepcion.ExcepcionValidacionDatos;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ControllerAdvisor extends ResponseEntityExceptionHandler {

    private static final Logger LOGGER_ERROR = LoggerFactory.getLogger(ControllerAdvisor.class);

    @ExceptionHandler(ExcepcionMutante.class)
    public ResponseEntity<String> handleExcepcionMutante(ExcepcionMutante ex) {
        LOGGER_ERROR.error(ex.getClass().getSimpleName(), ex);
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.ALREADY_REPORTED);
    }

    @ExceptionHandler(ExcepcionNoMutante.class)
    public ResponseEntity<String> handleExcepcionNoMutante(ExcepcionNoMutante ex) {
        LOGGER_ERROR.error(ex.getClass().getSimpleName(), ex);
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(ExcepcionValidacionDatos.class)
    public ResponseEntity<String> handleExcepcionValidacionDatos(ExcepcionValidacionDatos ex) {
        LOGGER_ERROR.error(ex.getClass().getSimpleName(), ex);
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

}
