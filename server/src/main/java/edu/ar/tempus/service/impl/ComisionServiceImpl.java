package edu.ar.tempus.service.impl;

import edu.ar.tempus.controller.dto.comision.UpdateComisionDTORequest;
import edu.ar.tempus.exceptions.business.EntityNotFoundException;
import edu.ar.tempus.exceptions.business.SuperPosicionDeHorariosException;
import edu.ar.tempus.model.Comision;
import edu.ar.tempus.model.Materia;
import edu.ar.tempus.model.Usuario;
import edu.ar.tempus.persistence.repository.ComisionRepository;
import edu.ar.tempus.persistence.sql.MateriaSQLDAO;
import edu.ar.tempus.persistence.sql.UsuarioDAOSQL;
import edu.ar.tempus.service.ComisionService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Transactional
public class ComisionServiceImpl implements ComisionService {

    private final ComisionRepository comisionRepository;

    private final MateriaSQLDAO materiaSQLDAO;

    private final UsuarioDAOSQL  usuarioDAO;

    public ComisionServiceImpl(ComisionRepository comisionRepository, MateriaSQLDAO materiaSQLDAO, UsuarioDAOSQL usuarioDAO) {
        this.comisionRepository = comisionRepository;
        this.materiaSQLDAO = materiaSQLDAO;
        this.usuarioDAO = usuarioDAO;
    }

    @Override
    public Comision guardar(Comision comision, Long materiaId) {
        Materia materia = findMateria(materiaId);

        comision.setMateria(materia);
        materia.agregarComision(comision);

        return comisionRepository.guardar(comision);
    }

    private Materia findMateria(Long materiaId) {
        return materiaSQLDAO.findById(materiaId).orElseThrow(() -> new EntityNotFoundException(Materia.class.getName(), materiaId));
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

    @Override
    public List<Materia> recuperarMateriasPorComision(List<Long> comisionIds) {
        return comisionRepository.recuperarMateriasPorComision(comisionIds);
    }

    @Override
    public Page<Comision> recuperarComisiones(int page, Long alumnoId) {
        Usuario alumno = usuarioDAO.findById(alumnoId).orElseThrow(() -> new EntityNotFoundException(Usuario.class.getName(), alumnoId));
        Pageable pageable = PageRequest.of(page, 9);
        return comisionRepository.recuperarComisiones(pageable, alumno.getCarreraActiva().getId());
    }

    @Override
    public Comision actualizar(Long idComision, UpdateComisionDTORequest updateComision) {
        Comision comision = comisionRepository.recuperar(idComision);

        Materia materia = findMateria(updateComision.materiaId());

        updateComision.actualizar(comision, materia);

        return comisionRepository.guardar(comision);
    }

}
