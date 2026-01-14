package edu.ar.tempus.controller.dto.comision;

import edu.ar.tempus.model.Comision;
import edu.ar.tempus.model.Materia;
import org.jspecify.annotations.Nullable;

import java.time.LocalTime;

public record ComisionDTOResponse(
        Long comisionId,
        LocalTime horarioInicio,
        LocalTime horarioFin,
        Materia materia
) {
    public static ComisionDTOResponse desdeModelo(Comision comision) {
        return new ComisionDTOResponse(
                comision.getComisionId(),
                comision.getHorarioInicio(),
                comision.getHorarioFin(),
                comision.getMateria()
        );
    }
}
