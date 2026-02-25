package edu.ar.tempus.persistence.repository;

import edu.ar.tempus.model.Comision;

public interface ComisionRepository {
    Comision guardar(Comision comision);

    Comision recuperar(Long comisionId);
}
