package edu.ar.tempus.service;

import edu.ar.tempus.model.Comision;

public interface ComisionService {
    Comision guardar(Comision comision);

    Comision recuperar(Long comisionId);
}
