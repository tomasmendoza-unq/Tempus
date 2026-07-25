package edu.ar.tempus.service.impl;

import edu.ar.tempus.exceptions.business.DependenciaCircularException;
import edu.ar.tempus.exceptions.business.RelacionCorrelativaYaExisteException;
import edu.ar.tempus.feature.alumno.model.Alumno;
import edu.ar.tempus.feature.alumno.service.AlumnoService;
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
    public List<Materia> recuperarMateriasDisponibles(List<Long> materiasAprobadas, Long idCarrera) {
        return materiaRepository.recuperarMateriasDisponibles(
                materiasAprobadas,
                idCarrera
        );
    }

    @Override
    public List<Materia> recuperarMateriasPorNombre(String nombreMateria) {
        return materiaRepository.recuperarMateriasPorNombre(nombreMateria);
    }

    @Override
    public List<Materia> guardarMaterias(List<Materia> materias) {
        return materiaRepository.saveAll(materias);
    }


}