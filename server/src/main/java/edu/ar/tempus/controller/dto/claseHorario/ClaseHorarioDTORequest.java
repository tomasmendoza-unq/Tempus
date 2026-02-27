package edu.ar.tempus.controller.dto.claseHorario;

import edu.ar.tempus.model.ClaseHorario;
import edu.ar.tempus.model.DiasSemana;

import java.time.LocalTime;

public record ClaseHorarioDTORequest(DiasSemana dia,
                                     LocalTime inicio,
                                     LocalTime fin) {
    public ClaseHorario aModelo() {
        return ClaseHorario.builder()
                .inicio(inicio)
                .fin(fin)
                .dia(dia)
                .build();
    }
}
