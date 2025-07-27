package com.tempus.Factory.impls;

import com.tempus.Factory.AbstractDTOFactory;
import com.tempus.dto.carrera.CarreraPostDTO;
import com.tempus.dto.carrera.CarreraResponseDTO;
import com.tempus.models.Carrera;
import org.springframework.stereotype.Component;

@Component
public class CarreraFactory extends AbstractDTOFactory {
    public Carrera toEntity(CarreraPostDTO dto) {
        return toEntity(dto, Carrera.class);
    }

    public CarreraResponseDTO toResponseDTO(Carrera saved) {
        return toDTO(saved, CarreraResponseDTO.class);
    }
}
