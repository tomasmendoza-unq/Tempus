package edu.ar.tempus.controller.dto.claseHorario;

import edu.ar.tempus.model.ClaseHorario;
import edu.ar.tempus.model.DiasSemana;

import java.time.LocalTime;

public record ClaseHorarioPreviewDTO(
        DiasSemana dia,
        LocalTime inicio,
        LocalTime fin
) {
    public static ClaseHorarioPreviewDTO desdeModelo(ClaseHorario claseHorario) {
        return new ClaseHorarioPreviewDTO(
                claseHorario.getDia(),
                claseHorario.getInicio(),
                claseHorario.getFin()
        );
    }
}