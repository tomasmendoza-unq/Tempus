package com.tempus.service;

import com.tempus.dto.carrera.CarreraPostDTO;
import com.tempus.dto.carrera.CarreraResponseDTO;
import com.tempus.dto.materia.MateriaResponseDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public interface ICarreraService {
    public CarreraResponseDTO createdCarrera(CarreraPostDTO dto);

    public CarreraResponseDTO getCarrera(Long id);

    public List<CarreraResponseDTO> getCarreras();

    public CarreraResponseDTO putCarrera(Long id, CarreraPostDTO carreraPostDTO);

    public void deleteCarrera(Long id);

    public List<MateriaResponseDTO> getMaterias(Long id);
}
