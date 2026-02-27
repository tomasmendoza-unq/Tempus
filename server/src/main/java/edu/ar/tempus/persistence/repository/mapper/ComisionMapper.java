package edu.ar.tempus.persistence.repository.mapper;

import edu.ar.tempus.model.Comision;
import edu.ar.tempus.persistence.neo4J.entity.ComisionNeo4J;
import edu.ar.tempus.persistence.neo4J.entity.MateriaNeo4J;

public interface ComisionMapper {
    ComisionNeo4J toNeo4J(Comision comisionGuardada, MateriaNeo4J materiaNeo);
}
