package edu.ar.tempus.service.impl;

import edu.ar.tempus.controller.dto.claseHorario.UpdateClaseHorarioDTORequest;
import edu.ar.tempus.exceptions.business.EntityNotFoundException;
import edu.ar.tempus.model.ClaseHorario;
import edu.ar.tempus.model.Usuario;
import edu.ar.tempus.persistence.repository.ComisionRepository;
import edu.ar.tempus.persistence.sql.ClaseHorarioSQLDAO;
import edu.ar.tempus.service.ClaseHorarioService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Service
@Transactional
@Validated
public class ClaseHorarioServiceImpl implements ClaseHorarioService {

    private final ClaseHorarioSQLDAO claseHorarioSQLDAO;


    public ClaseHorarioServiceImpl(ClaseHorarioSQLDAO claseHorarioSQLDAO, ComisionRepository comisionRepository) {
        this.claseHorarioSQLDAO = claseHorarioSQLDAO;
    }

    @Override
    public List<ClaseHorario> actualizar(@Valid List<UpdateClaseHorarioDTORequest> request) {
        //TODO: SOLUCIONAR ESTE PROBLEMA DE N+1
        return request.stream()
                .map(dto -> {

                    ClaseHorario clase = findClaseHorario(dto.id());
                    dto.aplicar(clase);

                    return claseHorarioSQLDAO.save(clase);

                })
                .toList();
    }

    public ClaseHorario findClaseHorario(Long id) {
        return claseHorarioSQLDAO.findById(id).orElseThrow(() -> new EntityNotFoundException(ClaseHorario.class.getName(), id));

    }

    @Override
    public void delete(Long idClaseHorario) {
        claseHorarioSQLDAO.delete(findClaseHorario(idClaseHorario));
    }
}
