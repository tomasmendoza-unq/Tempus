package com.tempus.service.impls;

import com.tempus.Factory.impls.CarreraFactory;
import com.tempus.data.IEntityFinder;
import com.tempus.data.impls.CarreraFinder;
import com.tempus.dto.carrera.CarreraPostDTO;
import com.tempus.dto.carrera.CarreraResponseDTO;
import com.tempus.models.Carrera;
import com.tempus.repository.ICarreraRepository;
import com.tempus.service.ICarreraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarreraService implements ICarreraService {

    @Autowired
    private ICarreraRepository carreraRepository;

    @Autowired
    private CarreraFactory carreraFactory;

    @Autowired
    private IEntityFinder<Carrera> finderCarrera;

    @Override
    public CarreraResponseDTO createdCarrera(CarreraPostDTO dto) {
        Carrera carrera = carreraFactory.toEntity(dto);
        Carrera saved = this.save(carrera);

        return this.toResponseDTO(saved);
    }

    @Override
    public CarreraResponseDTO getCarrera(Long id) {
        return this.toResponseDTO(this.findById(id));
    }

    @Override
    public List<CarreraResponseDTO> getCarreras() {
        return carreraRepository.findAll().stream().map(this::toResponseDTO).toList();
    }

    @Override
    public CarreraResponseDTO putCarrera(Long id, CarreraPostDTO carreraPostDTO) {
        Carrera carrera = this.findById(id);
        carreraFactory.updateEntity(carrera, carreraPostDTO);
        Carrera saved = this.save(carrera);

        return this.toResponseDTO(saved);
    }

    @Override
    public void deleteCarrera(Long id) {
        carreraRepository.delete(this.findById(id));
    }

    private Carrera findById(Long id) {
        return finderCarrera.findById(id);
    }

    private Carrera save(Carrera carrera) {
        return carreraRepository.save(carrera);
    }

    private CarreraResponseDTO toResponseDTO(Carrera carrera) {
        return carreraFactory.toResponseDTO(carrera);
    }
}
