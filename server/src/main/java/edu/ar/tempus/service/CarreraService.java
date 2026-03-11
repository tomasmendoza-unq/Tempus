package edu.ar.tempus.service;

import edu.ar.tempus.controller.dto.carrera.CarreraDTOBulkRequest;
import edu.ar.tempus.model.Carrera;

import java.util.List;
import java.util.Set;

public interface CarreraService {
    Carrera guardar(Carrera carrera, Set<Long> idsMaterias);

    Carrera recuperar(Long id);

    List<Carrera> recuperarTodos();

    List<Carrera> recuperarCarreraPorNombre(String lic);

    List<Carrera> recuperarCarrerasPorAlumno(Long alumnoId);

    void guardarCarreraCompleta(CarreraDTOBulkRequest carreraDTOBulkRequest);
}
