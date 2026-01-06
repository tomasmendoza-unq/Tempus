package edu.ar.tempus.service;

import edu.ar.tempus.model.Carrera;

public interface CarreraService {
    Carrera guardar(Carrera carrera);

    Carrera recuperar(Long id);
}
