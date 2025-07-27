package com.tempus.service;

import com.tempus.dto.carrera.CarreraPostDTO;
import com.tempus.dto.carrera.CarreraResponseDTO;
import org.springframework.stereotype.Service;

@Service
public interface ICarreraService {
    public CarreraResponseDTO createdCarrera(CarreraPostDTO dto);

    public CarreraResponseDTO getCarrera(Long id);
}
