package edu.ar.tempus.service;

import edu.ar.tempus.controller.dto.claseHorario.UpdateClaseHorarioDTORequest;
import edu.ar.tempus.model.ClaseHorario;
import jakarta.validation.Valid;

import java.util.List;

public interface ClaseHorarioService {
    public ClaseHorario findClaseHorario(Long id);

    List<ClaseHorario> actualizar(@Valid List<UpdateClaseHorarioDTORequest> request);

    void delete(Long idClaseHorario);
}
