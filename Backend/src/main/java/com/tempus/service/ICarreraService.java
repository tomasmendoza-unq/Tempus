package com.tempus.service;

import com.tempus.dto.carrera.CarreraPostDTO;
import com.tempus.dto.carrera.CarreraResponseDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ICarreraService {
    public CarreraResponseDTO createdCarrera(CarreraPostDTO dto);

    public CarreraResponseDTO getCarrera(Long id);

    public List<CarreraResponseDTO> getCarreras();
}
