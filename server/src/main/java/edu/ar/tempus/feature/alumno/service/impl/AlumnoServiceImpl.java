package edu.ar.tempus.feature.alumno.service.impl;

import edu.ar.tempus.exceptions.business.EntityNotFoundException;
import edu.ar.tempus.feature.alumno.service.AlumnoService;
import edu.ar.tempus.model.Carrera;
import edu.ar.tempus.model.Usuario;
import edu.ar.tempus.persistence.sql.CarreraDAOSQL;
import edu.ar.tempus.service.CarreraService;
import edu.ar.tempus.service.UsuarioService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlumnoServiceImpl implements AlumnoService {

    private final CarreraService carreraService;

    private final UsuarioService usuarioService;

    public AlumnoServiceImpl(CarreraService carreraService, UsuarioService usuarioService) {
        this.carreraService = carreraService;
        this.usuarioService = usuarioService;
    }

    @Override
    public List<Carrera> getCarrerasDisponibles(Long idAlumno) {
        return carreraService.recuperarCarrerasPorAlumno(idAlumno);
    }

    @Override
    public Carrera desuscribirseACarrera(Long idCarrera, Long idAlumno) {
        Carrera carrera = carreraService.recuperar(idCarrera);

        Usuario alumno = usuarioService.recuperarUsuarioPorId(idAlumno);

        alumno.desuscribirseACarrera(carrera);

        usuarioService.update(alumno);

        return carrera;
    }

    @Override
    public Carrera suscribirseACarrera(Long carreraId, Long alumnoId) {
        Carrera carrera = carreraService.recuperar(carreraId);

        Usuario alumno = usuarioService.recuperarUsuarioPorId(alumnoId);

        alumno.suscribirseACarrera(carrera);

        usuarioService.update(alumno);

        return carrera;
    }
}
