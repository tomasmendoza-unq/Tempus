package com.tempus.Factory.models;

import com.tempus.dto.carrera.CarreraPostDTO;
import com.tempus.dto.carrera.CarreraResponseDTO;
import com.tempus.models.Carrera;

public interface ICarreraFactory {
    Carrera toEntity(CarreraPostDTO dto);

    void updateEntity(Carrera carrera, CarreraPostDTO carreraPostDTO);

    CarreraResponseDTO toResponseDTO(Carrera carrera);
}
