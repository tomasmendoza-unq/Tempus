package com.tempus.data.impls;

import com.tempus.data.IMateriaFinder;
import com.tempus.exceptions.ResourceNotFound;
import com.tempus.models.Materia;
import com.tempus.repository.IMateriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MateriaFinder implements IMateriaFinder {
    @Autowired
    IMateriaRepository materiaRepository;

    @Override
    public List<Materia> findMateriasByCarreraId(Long idCarrera) {
        return materiaRepository.findMateriasByCarreraId(idCarrera);
    }

    @Override
    public Materia findById(Long id) {
        return materiaRepository.findById(id).orElseThrow(
                () -> new ResourceNotFound("No se encontro la materia")
        );
    }
}
