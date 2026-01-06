package edu.ar.tempus.service;

import edu.ar.tempus.model.Carrera;

import java.util.List;
import java.util.Set;

public interface CarreraService {
    Carrera guardar(Carrera carrera, Set<Long> idsMaterias);

    Carrera recuperar(Long id);

    List<Carrera> recuperarTodos();
}
