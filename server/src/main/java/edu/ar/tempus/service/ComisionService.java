package edu.ar.tempus.service;

import edu.ar.tempus.model.Comision;

public interface ComisionService {
    Comision guardar(Comision comision, Long materiaId);

    Comision recuperar(Long comisionId);
}
