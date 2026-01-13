package edu.ar.tempus.service.impl;

import edu.ar.tempus.exceptions.business.EntityNotFoundException;
import edu.ar.tempus.model.Carrera;
import edu.ar.tempus.model.Materia;
import edu.ar.tempus.persistence.sql.CarreraDAOSQL;
import edu.ar.tempus.persistence.sql.MateriaSQLDAO;
import edu.ar.tempus.service.CarreraService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@Transactional
public class CarreraServiceImpl implements CarreraService {

    private final CarreraDAOSQL carreraDAOSQL;

    private MateriaSQLDAO materiaSQLDAO;

    public CarreraServiceImpl(CarreraDAOSQL carreraDAOSQL, MateriaSQLDAO materiaSQLDAO) {
        this.carreraDAOSQL = carreraDAOSQL;
        this.materiaSQLDAO = materiaSQLDAO;
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
}
