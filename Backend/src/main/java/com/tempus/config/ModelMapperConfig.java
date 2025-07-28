package com.tempus.config;

import com.tempus.dto.materia.MateriaPostDTO;
import com.tempus.models.Materia;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();

        modelMapper.typeMap(MateriaPostDTO.class, Materia.class)
                .addMappings(mapper -> mapper.skip(Materia::setIdMateria));

        return modelMapper;
    }

}