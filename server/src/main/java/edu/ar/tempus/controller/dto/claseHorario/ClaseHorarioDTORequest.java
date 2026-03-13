package edu.ar.tempus.controller.dto.claseHorario;

import edu.ar.tempus.model.ClaseHorario;
import edu.ar.tempus.model.DiasSemana;
import jakarta.validation.constraints.AssertTrue;

import java.time.LocalTime;

public record ClaseHorarioDTORequest(DiasSemana dia,
                                     LocalTime inicio,
                                     LocalTime fin) {

    @AssertTrue(message = "El horario de fin debe ser posterior al horario de inicio")
    public boolean isHorarioValido() {
        return fin.isAfter(inicio);
    }
    public ClaseHorario aModelo() {
        return ClaseHorario.builder()
                .inicio(inicio)
                .fin(fin)
                .dia(dia)
                .build();
    }
}
