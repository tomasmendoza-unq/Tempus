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
        Carrera saved = carreraRepository.save(carrera);

        return carreraFactory.toResponseDTO(saved);
    }

    @Override
    public CarreraResponseDTO getCarrera(Long id) {
        return carreraFactory.toResponseDTO(finderCarrera.findById(id));
    }
}
