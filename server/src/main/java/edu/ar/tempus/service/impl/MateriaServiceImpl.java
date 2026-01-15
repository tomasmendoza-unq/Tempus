package edu.ar.tempus.service.impl;

import edu.ar.tempus.model.Materia;
import edu.ar.tempus.persistence.repository.MateriaRepository;
import edu.ar.tempus.service.MateriaService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

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

    @Override
    public void asociarMateria(Long materiaOrigenId, Long materiaDestinoId) {
        Materia materia = recuperar(materiaOrigenId);

        Materia correlativa = recuperar(materiaDestinoId);

        materiaRepository.crearRelacionCorrelativa(materiaOrigenId, materiaDestinoId);
    }

    @Override
    public List<Materia> recuperarTodos() {
        return materiaRepository.recuperarTodos();
    }

    @Override
    public List<Materia> recuperarMateriasDisponibles(List<Long> materiasAprobadas) {

        return materiaRepository.recuperarMateriasDisponibles(materiasAprobadas);
    }

    @Override
    public List<Materia> recuperarMateriasPorNombre(String nombreMateria) {
        return materiaRepository.recuperarMateriasPorNombre(nombreMateria);
    }
}