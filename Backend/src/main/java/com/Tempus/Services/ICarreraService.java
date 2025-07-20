package com.Tempus.Services;

import com.Tempus.DTO.CarreraDTO;
import com.Tempus.DTO.MateriaDTO;
import com.Tempus.DTO.MateriaResumenDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public interface ICarreraService {
    Set<MateriaResumenDTO> findMateriasOfCarreraById(long idCarrera);

    CarreraDTO findCarreraById(Long idCarrera);

    List<CarreraDTO> getCarreras();

    CarreraDTO createdCarrera(CarreraDTO carreraDTO);
}
