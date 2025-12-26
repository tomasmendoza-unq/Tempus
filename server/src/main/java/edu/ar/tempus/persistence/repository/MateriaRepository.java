package edu.ar.tempus.persistence.repository;

import edu.ar.tempus.model.Materia;

public interface MateriaRepository {
    Materia save(Materia ingles);

    Materia getById(Long materiaId);
}
