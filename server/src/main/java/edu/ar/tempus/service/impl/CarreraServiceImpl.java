package edu.ar.tempus.service.impl;

import edu.ar.tempus.controller.dto.carrera.CarreraDTOBulkRequest;
import edu.ar.tempus.controller.dto.materia.MateriaComisionDTORequest;
import edu.ar.tempus.controller.dto.materia.MateriaDTORequest;
import edu.ar.tempus.exceptions.business.EntityNotFoundException;
import edu.ar.tempus.model.Carrera;
import edu.ar.tempus.model.Materia;
import edu.ar.tempus.persistence.sql.CarreraDAOSQL;
import edu.ar.tempus.persistence.sql.MateriaSQLDAO;
import edu.ar.tempus.persistence.sql.UsuarioDAOSQL;
import edu.ar.tempus.service.CarreraService;
import edu.ar.tempus.service.MateriaService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
public class CarreraServiceImpl implements CarreraService {

    private final CarreraDAOSQL carreraDAOSQL;

    private final MateriaSQLDAO materiaSQLDAO;

    private final MateriaService materiaService;

    public CarreraServiceImpl(CarreraDAOSQL carreraDAOSQL, MateriaSQLDAO materiaSQLDAO, MateriaService materiaService) {
        this.carreraDAOSQL = carreraDAOSQL;
        this.materiaSQLDAO = materiaSQLDAO;
        this.materiaService = materiaService;
    }

    @Override
    public Carrera guardar(Carrera carrera, Set<Long> idsMaterias) {
        List<Materia> materias = materiaSQLDAO.findAllByIds(idsMaterias);

        carrera.setMaterias(materias);

        return carreraDAOSQL.save(carrera);
    }

    @Override
    public Carrera recuperar(Long id) {
        return carreraDAOSQL.findById(id).orElseThrow(() -> new EntityNotFoundException(Carrera.class.getName(), id));
    }

    @Override
    public List<Carrera> recuperarTodos() {
        return carreraDAOSQL.findAll();
    }

    @Override
    public List<Carrera> recuperarCarreraPorNombre(String nombreCarrera) {
        return carreraDAOSQL.findAllByNombreCarreraContainsIgnoreCase(nombreCarrera);
    }

    @Override
    public List<Carrera> recuperarCarrerasPorAlumno(Long alumnoId) {


        return carreraDAOSQL.recuperarCarerrasPorAlumno(alumnoId);
    }

    @Override
    public Carrera guardarCarreraCompleta(Carrera carrera) {
        List<Materia> materiasGuardadas = materiaService.guardarMaterias(carrera.getMaterias());

        Set<Long> materiaIds = materiasGuardadas.stream()
                .map(Materia::getMateriaId)
                .collect(Collectors.toSet());

        Carrera nuevaCarrera = Carrera.builder()
                .nombreCarrera(carrera.getNombreCarrera())
                .build();

        return this.guardar(nuevaCarrera, materiaIds);
    }
}
