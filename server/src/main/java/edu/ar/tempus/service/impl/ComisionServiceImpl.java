package edu.ar.tempus.service.impl;

import edu.ar.tempus.exceptions.business.EntityNotFoundException;
import edu.ar.tempus.model.Carrera;
import edu.ar.tempus.model.Comision;
import edu.ar.tempus.model.Materia;
import edu.ar.tempus.persistence.repository.ComisionRepository;
import edu.ar.tempus.persistence.sql.ComisionDAOSQL;
import edu.ar.tempus.persistence.sql.MateriaSQLDAO;
import edu.ar.tempus.service.ComisionService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class ComisionServiceImpl implements ComisionService {

    private final ComisionRepository comisionRepository;

    private final MateriaSQLDAO materiaSQLDAO;


    public ComisionServiceImpl(ComisionRepository comisionRepository, MateriaSQLDAO materiaSQLDAO) {
        this.comisionRepository = comisionRepository;
        this.materiaSQLDAO = materiaSQLDAO;
    }

    @Override
    public Comision guardar(Comision comision, Long materiaId) {
        Materia materia = materiaSQLDAO.findById(materiaId).orElseThrow(() -> new EntityNotFoundException(Materia.class.getName(), materiaId));

        comision.setMateria(materia);
        materia.agregarComision(comision);

        return comisionRepository.guardar(comision);
    }

    @Override
    public Comision recuperar(Long comisionId) {
        return comisionRepository.recuperar(comisionId);
    }

}
