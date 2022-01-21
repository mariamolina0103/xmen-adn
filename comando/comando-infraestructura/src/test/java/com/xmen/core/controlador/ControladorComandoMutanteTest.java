package com.xmen.core.controlador;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.xmen.core.ApplicationMock;
import com.xmen.core.comando.ComandoMutante;
import com.xmen.core.repositorio.RepositorioMutante;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = ApplicationMock.class)
@WebMvcTest(ControladorMutanteComando.class)
public class ControladorComandoMutanteTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RepositorioMutante repositorioMutante;

    @Test
    public void validarADNNulo() throws Exception {
        ComandoMutante comandoMutante = new ComandoMutante(null);

        this.mockMvc.perform(post("/mutant")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(comandoMutante)))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("El ADN es obligatorio por lo tanto no debe ser nulo o vac\u00EDo"));
    }

    @Test
    public void validarADNVacio() throws Exception {
        ComandoMutante comandoMutante = new ComandoMutante(new String[]{});

        this.mockMvc.perform(post("/mutant")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(comandoMutante)))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("El ADN es obligatorio por lo tanto no debe ser nulo o vac\u00EDo"));
    }

    @Test
    public void verificarItemsDuplicados() throws Exception {

        ComandoMutante comandoMutante = new ComandoMutante(new String[]{"TTTAG",
                "TTTAG",
                "TCCTC",
                "TTTAG",
                "CCCCA"});

        this.mockMvc.perform(post("/mutant")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(comandoMutante)))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("El ADN no puede contener secuencias duplicadas"));
    }

    @Test
    public void verificarLongitudDeItems() throws Exception {

        ComandoMutante comandoMutante = new ComandoMutante(new String[]{"ATG",
                "GTGC",
                "TTATGT",
                "CCCCTA",
                "TCAA"});

        this.mockMvc.perform(post("/mutant")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(comandoMutante)))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Todas las secuencias ingresadas del ADN deben tener la misma longitud"));
    }

    @Test
    public void verificarLongitudMinimaSecuencias() throws Exception {

        ComandoMutante comandoMutante = new ComandoMutante(new String[]{"CCC",
                "CAG",
                "TTA"});

        this.mockMvc.perform(post("/mutant")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(comandoMutante)))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Las secuencias ingresadas del ADN deben contener m\u00EDnimo 4 caracteres"));
    }

    @Test
    public void validarSecuenciasConOtrasLetrasAlasPermitidas() throws Exception {

        ComandoMutante comandoMutante = new ComandoMutante(new String[]{"ABCCGA",
                "BBBCGB",
                "TTATGT",
                "AGAAGG",
                "CCJCTM",
                "TCMCTG"});

        this.mockMvc.perform(post("/mutant")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(comandoMutante)))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Las secuencias ingresadas del ADN solo pueden contener las letras A, T, C y G"));
    }

    @Test
    public void verificarADNMutante() throws Exception {
        // arrange
        String[] dna = new String[]{"ATGCGA", "CAGTGC", "TTATGT", "AGAAGG", "CCCCTA", "TCACTG"};
        ComandoMutante comandoMutante = new ComandoMutante(dna);

        // act - assert
        this.mockMvc.perform(post("/mutant")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(comandoMutante)))
                .andExpect(status().isOk())
                .andExpect(content().string("true"));
    }

    @Test
    public void verificarADNNoMutante() throws Exception {

        String[] dna = new String[]{"TTCTGA", "CAGTAC", "TTATGT", "AGAAGG", "CCACTA", "TCACTG"};
        ComandoMutante comandoMutante = new ComandoMutante(dna);

        // act - assert
        this.mockMvc.perform(post("/mutant")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(comandoMutante)))
                .andExpect(status().isForbidden())
                .andExpect(content().string("El ADN enviado no pertene a un mutante"));
    }

    @Test
    public void verificarUrl() throws Exception {

        mockMvc.perform(post("/mutant")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }
}
