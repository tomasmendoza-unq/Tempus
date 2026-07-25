package edu.ar.tempus.feature.alumno.service.impl;

import edu.ar.tempus.feature.alumno.service.AlumnoService;
import edu.ar.tempus.model.Carrera;
import edu.ar.tempus.service.CarreraService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlumnoServiceImpl implements AlumnoService {

    private final CarreraService carreraService;

    public AlumnoServiceImpl(CarreraService carreraService) {
        this.carreraService = carreraService;
    }

    @Override
    public List<Carrera> getCarrerasDisponibles(Long idAlumno) {
        return carreraService.recuperarCarrerasPorAlumno(idAlumno);
    }
}
