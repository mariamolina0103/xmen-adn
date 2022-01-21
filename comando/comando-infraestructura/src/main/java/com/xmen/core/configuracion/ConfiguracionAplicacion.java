package com.xmen.core.configuracion;

import com.xmen.core.repositorio.RepositorioMutante;
import com.xmen.core.service.ServicioVerificarMutante;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfiguracionAplicacion {

    @Bean
    public ServicioVerificarMutante servicioVerificarMutante(RepositorioMutante repositorioMutante) {
        return new ServicioVerificarMutante(repositorioMutante);
    }

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper;
    }
}
