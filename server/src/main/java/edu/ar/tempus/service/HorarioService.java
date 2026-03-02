package edu.ar.tempus.service;

import edu.ar.tempus.model.Horario;

import java.util.List;
import java.util.Optional;

public interface HorarioService {
    List<Horario> generarNHorarioCon(List<Long> materiasIds,  Integer cantidadHorarios);
}
