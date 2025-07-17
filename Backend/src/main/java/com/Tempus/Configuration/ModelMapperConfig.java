package com.Tempus.Configuration;

import com.Tempus.DTO.CarreraDTO;
import com.Tempus.Models.Carrera;
import com.Tempus.Models.Materia;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.stream.Collectors;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();


        return modelMapper;
    }
}