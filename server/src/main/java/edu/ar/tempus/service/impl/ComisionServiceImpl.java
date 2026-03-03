package edu.ar.tempus.service.impl;

import edu.ar.tempus.exceptions.business.AlumnoAnotadoAOtraComisionException;
import edu.ar.tempus.exceptions.business.EntityNotFoundException;
import edu.ar.tempus.exceptions.business.SuperPosicionDeHorariosException;
import edu.ar.tempus.model.Comision;
import edu.ar.tempus.model.Materia;
import edu.ar.tempus.persistence.repository.ComisionRepository;
import edu.ar.tempus.persistence.sql.MateriaSQLDAO;
import edu.ar.tempus.service.ComisionService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.*;

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

    @Override
    public List<List<Comision>> encontrarIdsNCombinacionCompatible(List<Long> materiasIds, Integer cantidadHorarios) {

        return comisionRepository.encontrarCombinacionCompatible(materiasIds, cantidadHorarios);
    }

    @Override
    public List<Comision> recuperarPorIds(List<Long> comisionIds) {
        return comisionRepository.findAll(comisionIds);
    }

    @Override
    public void validarSuperPosicion(List<Long> comisionIds, List<Long> comisionesAnotadas) {
        if(comisionRepository.haySuperposicionHoraria(comisionIds, comisionesAnotadas)) throw new SuperPosicionDeHorariosException("El alumno ya se encuentra inscripto en una de las comisiones");
    }

    @Override
    public boolean hayComisionesDeMismaMateriaEnNuevas(List<Long> comisionIds) {
        return comisionRepository.hayComisionesDeMismaMateriaEnNuevas(comisionIds);
    }

}
