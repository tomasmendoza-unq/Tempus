package edu.ar.tempus.service;

import edu.ar.tempus.model.Materia;

import java.util.List;

public interface MateriaService {
    Materia guardar(Materia materia);

    Materia recuperar(Long materiaId);

    void asociarMateria(Long materiaId, Long materiaId1);

    List<Materia> recuperarTodos();

    List<Materia> recuperarMateriasDisponibles(List<Long> materiasAprobadas);
}
