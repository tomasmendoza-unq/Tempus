package edu.ar.tempus.service.impl;

import edu.ar.tempus.model.Materia;
import edu.ar.tempus.persistence.repository.MateriaRepository;
import edu.ar.tempus.service.MateriaService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class MateriaServiceImpl implements MateriaService {


    private final MateriaRepository materiaRepository;

    public MateriaServiceImpl(MateriaRepository materiaRepository) {
        this.materiaRepository = materiaRepository;
    }

    @Override
    public Materia guardar(Materia materia) {
        return materiaRepository.save(materia);
    }

    @Override
    public Materia recuperar(Long materiaId) {
        return materiaRepository.getById(materiaId);
    }
}
