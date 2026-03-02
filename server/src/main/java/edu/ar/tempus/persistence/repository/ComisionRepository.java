package edu.ar.tempus.persistence.repository;

import edu.ar.tempus.model.Comision;
import edu.ar.tempus.persistence.neo4J.entity.ComisionNeo4J;

import java.util.List;

public interface ComisionRepository {
    Comision guardar(Comision comision);

    Comision recuperar(Long comisionId);

    List<List<Comision>> encontrarCombinacionCompatible(List<Long> materiasIds, Integer cantidadHorarios);

    List<Comision> findAll(List<Long> comisionesCompatibles);
}
