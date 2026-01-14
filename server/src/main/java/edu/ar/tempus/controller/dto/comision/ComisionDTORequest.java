package edu.ar.tempus.controller.dto.comision;

import edu.ar.tempus.model.Comision;
import edu.ar.tempus.model.Materia;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotNull;

import java.time.LocalTime;

public record ComisionDTORequest(
        @NotNull(message = "El horario de inicio es requerido")
        LocalTime horarioInicio,

        @NotNull(message = "El horario de fin es requerido")
        LocalTime horarioFin,

        @NotNull(message = "La materia es requerida")
        Long materiaId
) {


    @AssertTrue(message = "El horario de fin debe ser posterior al horario de inicio")
    private boolean isHorarioValido() {
        return horarioFin.isAfter(horarioInicio);
    }

    public static Comision aModelo(ComisionDTORequest request) {
        return Comision.builder()
                .horarioInicio(request.horarioInicio)
                .horarioFin(request.horarioFin)
                .build();
    }
}
