package edu.ar.tempus.controller.dto.comision;

import edu.ar.tempus.model.Comision;
import edu.ar.tempus.model.Materia;

import java.time.LocalTime;

public record ComisionDTORequest(
        LocalTime horarioInicio,
        LocalTime horarioFin,
        Long materiaId
) {
    public static Comision aModelo(ComisionDTORequest request) {
        return Comision.builder()
                .horarioInicio(request.horarioInicio)
                .horarioFin(request.horarioFin)
                .build();
    }
}
