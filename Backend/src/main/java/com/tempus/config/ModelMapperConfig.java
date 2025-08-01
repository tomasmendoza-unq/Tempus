package com.tempus.config;

import com.tempus.dto.comision.ComisionPostDTO;
import com.tempus.dto.materia.MateriaPostDTO;
import com.tempus.dto.materia.MateriaResponseDTO;
import com.tempus.models.Comision;
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
                .addMappings(mapper -> mapper.skip(Materia::setCorrelativas))
                .addMappings(mapper -> mapper.skip(Materia::setIdMateria));
        modelMapper.typeMap(Materia.class, MateriaResponseDTO.class)
                .addMappings(mapper -> mapper.skip(MateriaResponseDTO::setCorrelativas));
        modelMapper.typeMap(ComisionPostDTO.class, Comision.class)
                .addMappings(mapper -> mapper.skip(Comision::setIdComision));

        return modelMapper;
    }

}