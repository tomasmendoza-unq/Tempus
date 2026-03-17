package edu.ar.tempus.controller.dto.claseHorario;

import edu.ar.tempus.model.ClaseHorario;
import edu.ar.tempus.model.DiasSemana;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotNull;

import java.time.LocalTime;

public record ClaseHorarioDTOResponse(
        Long id,
        @NotNull(message = "El dia que se dicta es requerido")
         DiasSemana dia,
         @NotNull(message = "La hora inicio es requerida")
         LocalTime inicio,
        @NotNull(message = "La hora fin es requerida")
         LocalTime fin
) {


    public static ClaseHorarioDTOResponse desdeModelo(ClaseHorario clases) {
        return new ClaseHorarioDTOResponse(
                clases.getId(),
                clases.getDia(),
                clases.getInicio(),
                clases.getFin()
        );
    }
}
