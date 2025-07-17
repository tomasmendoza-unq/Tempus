package com.Tempus.Services.impls;

import com.Tempus.DTO.CarreraDTO;
import com.Tempus.DTO.MateriaDTO;
import com.Tempus.Exceptions.ResourceNotFound;
import com.Tempus.Models.Carrera;
import com.Tempus.Models.Materia;
import com.Tempus.Repository.ICarreraRepository;
import com.Tempus.Services.ICarreraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CarreraService implements ICarreraService {

    @Autowired
    private ICarreraRepository carreraRepository;

    @Override
    public Set<MateriaDTO> findMateriasOfCarreraById(long idCarrera) {
        return this.findCarreraById(idCarrera).getMaterias();
    }

    @Override
    public CarreraDTO findCarreraById(Long idCarrera) {
        return this.toDTO(this.findById(idCarrera));
    }

    private CarreraDTO toDTO(Carrera carrera) {
        return CarreraDTO.builder()
                .nombre(carrera.getNombre())
                .materias(carrera.getMaterias().stream().map(Materia::toDTO).collect(Collectors.toSet()))
                .build();
    }

    private Carrera findById(Long idCarrera) {
        return carreraRepository
                .findById(idCarrera)
                .orElseThrow(
                        () -> new ResourceNotFound("No se encontro la carrera")
                );
    }

    @Override
    public List<CarreraDTO> getCarreras() {
        return this.findyAll().stream().map(this::toDTO).toList();
    }

    private List<Carrera> findyAll() {
        return carreraRepository.findAll();
    }
}
