package com.Tempus.Configuration;
import com.Tempus.DTO.ComisionCreatedDTO;
import com.Tempus.Models.Comision;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();

        modelMapper.addMappings(new PropertyMap<Comision, ComisionCreatedDTO>() {
            @Override
            protected void configure() {
                map().setIdMateria(source.getMateria().getId());
                map().setCuatrimestre(source.getCuatrimestre());
                map().setTurno(source.getTurno());
            }
        });

        modelMapper.addMappings(new PropertyMap<ComisionCreatedDTO, Comision>() {
            @Override
            protected void configure() {
                map().setCuatrimestre(source.getCuatrimestre());
                map().setTurno(source.getTurno());
                skip(destination.getMateria());
            }
        });


        return modelMapper;
    }
}