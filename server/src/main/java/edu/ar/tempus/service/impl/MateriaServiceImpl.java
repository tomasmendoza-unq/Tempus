package edu.ar.tempus.service.impl;

import edu.ar.tempus.model.Materia;
import edu.ar.tempus.persistence.repository.MateriaRepository;
import edu.ar.tempus.service.MateriaService;
import edu.ar.tempus.service.UsuarioService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class MateriaServiceImpl implements MateriaService {

    private final MateriaRepository materiaRepository;

    private final UsuarioService usuarioService;

    public MateriaServiceImpl(MateriaRepository materiaRepository, UsuarioService usuarioService) {
        this.materiaRepository = materiaRepository;
        this.usuarioService = usuarioService;
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
    public List<Materia> recuperarMateriasDisponibles(Long alumnoId) {
        List<Long> materiasAprobadas = usuarioService.recuperarMateriasAprobadasPorAlumno(alumnoId);

        return materiaRepository.recuperarMateriasDisponibles(materiasAprobadas);
    }

    @Override
    public List<Materia> recuperarMateriasPorNombre(String nombreMateria) {
        return materiaRepository.recuperarMateriasPorNombre(nombreMateria);
    }


}