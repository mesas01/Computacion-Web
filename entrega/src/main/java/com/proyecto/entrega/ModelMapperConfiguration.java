package com.proyecto.entrega;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfiguration {
    @Bean
    public ModelMapper modelMapper() {
        // Aquí se crea el objeto ModelMapper, que sirve para convertir
        // entre DTOs (formularios) y Entidades (tablas de la BD).
        ModelMapper modelMapper = new ModelMapper();
        // Configuramos el mapper en modo ESTRICTO:
        // Solo mapeará campos que tengan EXACTAMENTE el mismo nombre
        // en el DTO y en la Entidad. UsuarioController → recibe UsuarioDTO, lo convierte a Usuario
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        return modelMapper;
    }
}
