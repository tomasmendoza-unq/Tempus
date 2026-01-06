package edu.ar.tempus.service.impl;

import edu.ar.tempus.exceptions.business.EntityNotFoundException;
import edu.ar.tempus.model.Carrera;
import edu.ar.tempus.model.Materia;
import edu.ar.tempus.persistence.sql.CarreraDAOSQL;
import edu.ar.tempus.service.CarreraService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class CarreraServiceImpl implements CarreraService {

    private final CarreraDAOSQL carreraDAOSQL;

    public CarreraServiceImpl(CarreraDAOSQL carreraDAOSQL) {
        this.carreraDAOSQL = carreraDAOSQL;
    }

    @Override
    public Carrera guardar(Carrera carrera) {
        return carreraDAOSQL.save(carrera);
    }

    @Override
    public Carrera recuperar(Long id) {
        return carreraDAOSQL.findById(id).orElseThrow(() -> new EntityNotFoundException(Carrera.class.getName(), id));
    }
}
