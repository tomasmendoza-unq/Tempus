package edu.ar.tempus.persistence.repository;

import edu.ar.tempus.model.Materia;

import java.util.List;

public interface MateriaRepository {
    Materia save(Materia ingles);

    Materia getById(Long materiaId);

    void crearRelacionCorrelativa(Long materiaOrigenId, Long materiaDestinoId);

    List<Materia> recuperarTodos();
}
