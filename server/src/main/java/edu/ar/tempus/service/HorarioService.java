package edu.ar.tempus.service;

import edu.ar.tempus.controller.dto.claseHorario.UpdateClaseHorarioDTORequest;
import edu.ar.tempus.model.ClaseHorario;
import edu.ar.tempus.model.Horario;

import java.util.List;

public interface HorarioService {
    List<Horario> generarNHorarioCon(List<Long> materiasIds,  Integer cantidadHorarios);

    List<ClaseHorario> actualizar(List<UpdateClaseHorarioDTORequest> request);
}
