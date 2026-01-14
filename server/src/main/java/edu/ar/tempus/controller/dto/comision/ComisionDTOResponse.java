package edu.ar.tempus.controller.dto.comision;

import edu.ar.tempus.controller.dto.materia.MateriaDTOResponse;
import edu.ar.tempus.controller.dto.materia.MateriaDTOResponseSimple;
import edu.ar.tempus.model.Comision;
import edu.ar.tempus.model.Materia;
import org.jspecify.annotations.Nullable;

import java.time.LocalTime;

public record ComisionDTOResponse(
        Long comisionId,
        LocalTime horarioInicio,
        LocalTime horarioFin,
        MateriaDTOResponseSimple materia
) {
    public static ComisionDTOResponse desdeModelo(Comision comision) {
        return new ComisionDTOResponse(
                comision.getComisionId(),
                comision.getHorarioInicio(),
                comision.getHorarioFin(),
                MateriaDTOResponseSimple.desdeModelo(comision.getMateria())
        );
    }
}
