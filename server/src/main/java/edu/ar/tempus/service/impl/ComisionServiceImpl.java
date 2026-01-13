package edu.ar.tempus.service.impl;

import edu.ar.tempus.exceptions.business.EntityNotFoundException;
import edu.ar.tempus.model.Carrera;
import edu.ar.tempus.model.Comision;
import edu.ar.tempus.persistence.sql.ComisionDAOSQL;
import edu.ar.tempus.service.ComisionService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class ComisionServiceImpl implements ComisionService {


    private final ComisionDAOSQL comisionDAOSQL;

    public ComisionServiceImpl(ComisionDAOSQL comisionDAOSQL) {
        this.comisionDAOSQL = comisionDAOSQL;
    }

    @Override
    public Comision guardar(Comision comision) {
        return comisionDAOSQL.save(comision);
    }

    @Override
    public Comision recuperar(Long comisionId) {
        return comisionDAOSQL.findById(comisionId).orElseThrow(() -> new EntityNotFoundException(Carrera.class.getName(), comisionId));
    }
}
