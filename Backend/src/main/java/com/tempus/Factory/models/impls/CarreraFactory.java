package com.tempus.Factory.models.impls;

import com.tempus.Factory.models.AbstractDTOFactory;
import com.tempus.Factory.models.ICarreraFactory;
import com.tempus.dto.carrera.CarreraPostDTO;
import com.tempus.dto.carrera.CarreraResponseDTO;
import com.tempus.models.Carrera;
import org.springframework.stereotype.Component;

@Component
public class CarreraFactory extends AbstractDTOFactory implements ICarreraFactory {
    public Carrera toEntity(CarreraPostDTO dto) {
        return toEntity(dto, Carrera.class);
    }

    @Override
    public void updateEntity(Carrera carrera, CarreraPostDTO carreraPostDTO) {
        carrera.setNombreCarrera(carreraPostDTO.getNombreCarrera());
    }

    public CarreraResponseDTO toResponseDTO(Carrera saved) {
        return toDTO(saved, CarreraResponseDTO.class);
    }


}
