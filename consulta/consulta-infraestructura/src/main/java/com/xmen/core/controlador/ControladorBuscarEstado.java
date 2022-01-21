package com.xmen.core.controlador;

import com.xmen.core.modelo.EstadoDTO;
import com.xmen.core.manejador.ManejadorBuscarEstado;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("stats")
@Tag(name = "Estado de las verificaciones", description = "Controlador para consultar el estado de las verificaciones de ADN")
public class ControladorBuscarEstado {

    private final ManejadorBuscarEstado manejadorBuscarEstado;

    @Autowired
    public ControladorBuscarEstado(ManejadorBuscarEstado manejadorBuscarEstado) {
        this.manejadorBuscarEstado = manejadorBuscarEstado;
    }

    @GetMapping
    @ResponseStatus(code = HttpStatus.OK)
    @Operation(summary = "Buscar el estado de las verificaciones de ADN")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Se retorna el estado de las verificaciones de ADN", content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "500", description = "Error interno obteniendo el estado", content = {@Content(mediaType = "application/json")})
    })
    public ResponseEntity<EstadoDTO> buscarEstado() {
        return new ResponseEntity<>(this.manejadorBuscarEstado.ejecutar(), HttpStatus.OK);
    }
}
