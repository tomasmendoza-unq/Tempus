package edu.ar.tempus.persistence.repository;

import edu.ar.tempus.model.Materia;
import edu.ar.tempus.model.usuario.Usuario;

import java.util.List;

public interface MateriaRepository {
    Materia save(Materia ingles);

    Materia getById(Long materiaId);

    void crearRelacionCorrelativa(Long materiaOrigenId, Long materiaDestinoId);

    List<Materia> recuperarTodos();

    List<Materia> recuperarMateriasDisponibles(List<Long> materiasAprobadas, Long id);

    List<Materia> recuperarMateriasPorNombre(String nombreMateria);

    void crearRelacionesCorrelativas(Long materiaId, List<Long> materiaIds);

    boolean existeRelacionCorrelativa(Long materiaOrigenId, Long materiaDestinoId);

    boolean existeDependenciaCircular(Long materiaOrigenId, Long materiaDestinoId);

    boolean validarSiCuentaConLasCorrelativas(Usuario alumno, List<Long> comisionIds);

    List<Materia> saveAll(List<Materia> materias);
}
