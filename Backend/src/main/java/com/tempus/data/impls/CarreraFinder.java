package com.tempus.data.impls;

import com.tempus.data.IEntityFinder;
import com.tempus.exceptions.ResourceNotFound;
import com.tempus.models.Carrera;
import com.tempus.repository.ICarreraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CarreraFinder implements IEntityFinder<Carrera> {

    @Autowired
    ICarreraRepository carreraRepository;

    @Override
    public Carrera findById(Long id) {
        return carreraRepository.findById(id).orElseThrow(
                () -> new ResourceNotFound("No se encontro la carrera")
        );
    }

    @Override
    public List<Carrera> findAll() {
        return carreraRepository.findAll();
    }
}
