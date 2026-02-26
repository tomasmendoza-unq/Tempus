package edu.ar.tempus.persistence.repository;

import edu.ar.tempus.model.Comision;

import java.util.List;

public interface ComisionRepository {
    Comision guardar(Comision comision);

    Comision recuperar(Long comisionId);

    List<Comision> encontrarIdsUnaCombinacionCompatible(List<Long> materiasIds);
}
