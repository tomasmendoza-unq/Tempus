package edu.ar.tempus.controller.dto.comision;

import edu.ar.tempus.model.Comision;

import java.time.LocalTime;

public record ComisionDTOResponseSimple(
        Long comisionId,
        LocalTime horarioInicio,
        LocalTime horarioFin
) {

    public static ComisionDTOResponseSimple desdeModelo(Comision comision) {
        return new ComisionDTOResponseSimple(
                comision.getComisionId(),
                comision.getHorarioInicio(),
                comision.getHorarioFin()
        );
    }
}
