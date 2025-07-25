package com.Tempus.Services.impls;

import com.Tempus.DTO.CarreraDTO;
import com.Tempus.DTO.MateriaDTO;
import com.Tempus.DTO.MateriaResumenDTO;
import com.Tempus.Exceptions.ResourceNotFound;
import com.Tempus.Factory.IMateriaFactory;
import com.Tempus.Models.Carrera;
import com.Tempus.Models.Materia;
import com.Tempus.Repository.ICarreraRepository;
import com.Tempus.Repository.IMateriaRepository;
import com.Tempus.Services.ICarreraService;
import com.Tempus.Services.IMateriaService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CarreraService implements ICarreraService {

    @Autowired
    private ICarreraRepository carreraRepository;

    @Autowired
    private IMateriaRepository materiaRepository;

    @Override
    @Transactional
    public Set<MateriaResumenDTO> findMateriasOfCarreraById(Long idCarrera) {
        return this.findCarreraById(idCarrera).getMaterias();
    }

    @Override
    public CarreraDTO findCarreraById(Long idCarrera) {
        return this.toDTO(this.findById(idCarrera));
    }

    private CarreraDTO toDTO(Carrera carrera) {
        Set<MateriaResumenDTO> materias = materiaRepository.buscarMateriasPorCarrera(carrera.getId_carrera());

        return CarreraDTO.builder()
                .nombre(carrera.getNombre())
                .materias(materias)
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

    @Override
    public CarreraDTO createdCarrera(CarreraDTO carreraDTO) {
        Carrera carrera = this.save(carreraDTO);
        return this.toDTO(carrera);
    }

    private Carrera save(CarreraDTO carreraDTO) {
        return carreraRepository.save(this.toEntity(carreraDTO));
    }

    private Carrera toEntity(CarreraDTO carreraDTO) {
        return Carrera
                .builder()
                .nombre(carreraDTO.getNombre())
                .build();
    }

    private List<Carrera> findyAll() {
        return carreraRepository.findAll();
    }
}
