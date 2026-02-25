package edu.ar.tempus.controller.dto.claseHorario;

import edu.ar.tempus.model.ClaseHorario;
import edu.ar.tempus.model.DiasSemana;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotNull;

import java.time.LocalTime;

public record ClaseHorarioDTOResponse(
        @NotNull(message = "El dia que se dicta es requerido")
         DiasSemana dia,
         @NotNull(message = "La hora inicio es requerida")
         LocalTime inicio,
        @NotNull(message = "La hora fin es requerida")
         LocalTime fin
) {
    @AssertTrue(message = "El horario de fin debe ser posterior al horario de inicio")
    public boolean isHorarioValido() {
        return fin.isAfter(inicio);
    }

    public static ClaseHorarioDTOResponse desdeModelo(ClaseHorario clases) {
        return new ClaseHorarioDTOResponse(
                clases.getDia(),
                clases.getInicio(),
                clases.getFin()
        );
    }
}
