package com.xmen.core.modelo;
import com.xmen.core.excepcion.ExcepcionValidacionDatos;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;


public class MutanteDTOTest {

    @Test
    public void validarDatosNulos() {
        Throwable excepcion = Assertions.assertThrows(ExcepcionValidacionDatos.class, () -> new MutanteDTO(null));
        Assertions.assertEquals("El ADN es obligatorio por lo tanto no debe ser nulo o vac\u00EDo", excepcion.getMessage());
    }

    @Test
    public void validarDatosVacios() {
        Throwable excepcion = Assertions.assertThrows(ExcepcionValidacionDatos.class, () -> new MutanteDTO(new String[]{}));
        Assertions.assertEquals("El ADN es obligatorio por lo tanto no debe ser nulo o vac\u00EDo", excepcion.getMessage());
    }

    @Test
    public void validateDuplicateDataCorrectlyTest() {
        Throwable excepcion = Assertions.assertThrows(ExcepcionValidacionDatos.class, () -> new MutanteDTO(new String[]{"AAAAAA", "AAAAAA", "TTATGT", "AGGGGG", "CCCCTA", "TCCCTG"}));
        Assertions.assertEquals("El ADN no puede contener secuencias duplicadas", excepcion.getMessage());
    }

    @Test
    public void validarLongitudDeItems() {
        Throwable excepcion = Assertions.assertThrows(ExcepcionValidacionDatos.class, () -> new MutanteDTO(new String[]{"CCCCC", "CAGGGC", "TAATGT", "AGAGG", "CCAAATA", "TCAA"}));
        Assertions.assertEquals("Todas las secuencias ingresadas del ADN deben tener la misma longitud", excepcion.getMessage());
    }

    @Test
    public void validarLongituMinimaItems() {
        Throwable excepcion = Assertions.assertThrows(ExcepcionValidacionDatos.class, () -> new MutanteDTO(new String[]{"CCC","CAG","TTA"}));
        Assertions.assertEquals("Las secuencias ingresadas del ADN deben contener m\u00EDnimo 4 caracteres", excepcion.getMessage());
    }

    @Test
    public void validarLetrasDeSecuencia() {
        Throwable excepcion = Assertions.assertThrows(ExcepcionValidacionDatos.class, () -> new MutanteDTO(new String[]{"ABCCGA",
                "BBBCGB",
                "TTATGT",
                "AGAAGG",
                "CCJCTM",
                "TCMCTG"}));
        Assertions.assertEquals("Las secuencias ingresadas del ADN solo pueden contener las letras A, T, C y G", excepcion.getMessage());
    }

    @Test
    public void validarObligatorieradDelAtributoADN() {
        MutanteDTO mutante = new MutanteDTO(new String[]{
                "ATTTGA",
                "CAGTAC",
                "TTAAGT",
                "AGAAGG",
                "CCACTA",
                "TCCCTG"});
        Assertions.assertNotNull(mutante.getAdn());
    }

}