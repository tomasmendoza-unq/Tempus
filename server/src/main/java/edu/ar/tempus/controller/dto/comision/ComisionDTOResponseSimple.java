package edu.ar.tempus.controller.dto.comision;

import edu.ar.tempus.controller.dto.claseHorario.ClaseHorarioDTOResponse;
import edu.ar.tempus.controller.dto.materia.MateriaDTOResponseSimple;
import edu.ar.tempus.model.Comision;

import java.time.LocalTime;
import java.util.List;

public record ComisionDTOResponseSimple(
        Long comisionId,
        List<ClaseHorarioDTOResponse> claseHorario,
        MateriaDTOResponseSimple materia
) {

    public static ComisionDTOResponseSimple desdeModelo(Comision comision) {
        return new ComisionDTOResponseSimple(
                comision.getComisionId(),
                comision.getClases().stream().map(ClaseHorarioDTOResponse::desdeModelo).toList(),
                MateriaDTOResponseSimple.desdeModelo(comision.getMateria())
        );
    }
}
