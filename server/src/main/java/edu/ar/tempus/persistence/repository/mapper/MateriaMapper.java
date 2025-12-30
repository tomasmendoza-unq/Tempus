package edu.ar.tempus.persistence.repository.mapper;

import edu.ar.tempus.model.Materia;
import edu.ar.tempus.persistence.neo4J.entity.MateriaNeo4J;

import java.util.Set;

public interface MateriaMapper {
    MateriaNeo4J toNeo4J(Materia materia);

    Materia toModel(Materia materia, MateriaNeo4J materiaNeo4J);
}
