package com.xmen.core.controlador;

import com.xmen.core.ApplicationMock;
import com.xmen.core.repositorio.EstadoReposiorio;
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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = ApplicationMock.class)
@WebMvcTest(ControladorBuscarEstado.class)
public class ControladorConsultaEstadoTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EstadoReposiorio estadoReposiorio;

    @Test
    public void consultarEstados() throws Exception {
        mockMvc.perform(get("/stats").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void verificarUrl() throws Exception {
        mockMvc.perform(get("/sttac").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
}
