package edu.ar.tempus.feature.alumno.service;

import edu.ar.tempus.model.Carrera;

import java.util.List;

public interface AlumnoService {
    List<Carrera> getCarrerasDisponibles(Long idAlumno);
}
