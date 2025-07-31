package com.tempus.data.impls;

import com.tempus.data.IComisionFinder;
import com.tempus.exceptions.ResourceNotFound;
import com.tempus.models.Comision;
import com.tempus.repository.IComisionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ComisionFinder implements IComisionFinder {

    @Autowired
    IComisionRepository comisionRepository;

    @Override
    public Comision findById(Long id) {
        return comisionRepository.findById(id).orElseThrow(
                () -> new ResourceNotFound("No se encontro la comision")
        );
    }



    @Override
    public List<Comision> findAll() {
        return comisionRepository.findAllWithMateria();
    }

    @Override
    public Comision findComisionWithMateria(Long id) {
        return comisionRepository.findComisionWithMateria(id).orElseThrow(
                () -> new ResourceNotFound("No se encontro la comision")
        );
    }
}
