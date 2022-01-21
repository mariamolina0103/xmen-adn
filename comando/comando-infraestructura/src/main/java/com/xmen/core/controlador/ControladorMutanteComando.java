package com.xmen.core.controlador;

import com.xmen.core.comando.ComandoMutante;
import com.xmen.core.manejador.ManejadorMutante;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("mutant")
@Tag(name = "Validacion de ADN humano", description = "Controlador para validar si un humano es mutante")
public class ControladorMutanteComando {

    private final ManejadorMutante manejadorMutante;

    @Autowired
    public ControladorMutanteComando(ManejadorMutante manejadorMutante) {
        this.manejadorMutante = manejadorMutante;
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.OK)
    @Operation(summary = "Validar si el ADN ingresado corresponde a un mutante")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "500", description = "Error en el servidor"),
            @ApiResponse(responseCode = "200", description = "El ADN ingresado corresponde a un mutante"),
            @ApiResponse(responseCode = "208", description = "El ADN ingresado ya se valido previamente"),
            @ApiResponse(responseCode = "403", description = "El ADN ingresado no corresponde a un mutante"),
            @ApiResponse(responseCode = "400", description = "Los datos ingresados no son correctos para la verificacion de ADN"),
    })
    public ResponseEntity<Boolean> validar(
            @Parameter(name = "comandoMutante", description = "El objeto comandoMutante contienen los atributos necesarios para validar si el ADN pertenece a un mutante", required = true)
            @RequestBody ComandoMutante comandoMutante) {
        return new ResponseEntity<>(this.manejadorMutante.ejecutar(comandoMutante), HttpStatus.OK);
    }
}
