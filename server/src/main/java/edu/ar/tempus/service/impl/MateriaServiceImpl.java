package edu.ar.tempus.service.impl;

import edu.ar.tempus.exceptions.business.DependenciaCircularException;
import edu.ar.tempus.exceptions.business.RelacionCorrelativaYaExisteException;
import edu.ar.tempus.model.Materia;
import edu.ar.tempus.model.Usuario;
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
        if (materiaRepository.existeRelacionCorrelativa(materiaOrigenId, materiaDestinoId)) {
            throw new RelacionCorrelativaYaExisteException(materiaOrigenId, materiaDestinoId);
        }
        if (materiaRepository.existeDependenciaCircular(materiaOrigenId, materiaDestinoId)) {
            throw new DependenciaCircularException(materiaOrigenId, materiaDestinoId);
        }

        materiaRepository.crearRelacionCorrelativa(materiaOrigenId, materiaDestinoId);
    }

    @Override
    public void asociarMaterias(Long materiaId, List<Long> materiaIds) {
        materiaRepository.crearRelacionesCorrelativas(materiaId, materiaIds);
    }

    @Override
    public List<Materia> recuperarTodos() {
        return materiaRepository.recuperarTodos();
    }

    @Override
    public List<Materia> recuperarMateriasDisponibles(Long alumnoId) {
        List<Long> materiasAprobadas = usuarioService.recuperarMateriasAprobadasPorAlumno(alumnoId);
        Usuario alumno = usuarioService.recuperarUsuarioPorId(alumnoId);
        List<Long> materiasPorCarreraActiva = materiaRepository.recuperarMateriasPorCarrera(materiasAprobadas, alumno.getCarreraActiva().getId());

        return materiaRepository.recuperarMateriasDisponibles(materiasPorCarreraActiva);
    }

    @Override
    public List<Materia> recuperarMateriasPorNombre(String nombreMateria) {
        return materiaRepository.recuperarMateriasPorNombre(nombreMateria);
    }


}