package edu.ar.tempus.controller.dto.claseHorario;

import edu.ar.tempus.model.ClaseHorario;
import edu.ar.tempus.model.DiasSemana;
import jakarta.validation.constraints.AssertTrue;

import java.time.LocalTime;

public record UpdateClaseHorarioDTORequest(
        Long id,
        DiasSemana dia,
        LocalTime inicio,
        LocalTime fin
){
    @AssertTrue(message = "El horario de fin debe ser posterior al horario de inicio")
    public boolean isHorarioValido() {

        if (inicio == null || fin == null) {
            return true;
        }

        return fin.isAfter(inicio);
    }

    public void aplicar(ClaseHorario clase) {

        if (dia != null) {
            clase.setDia(dia);
        }

        if (inicio != null) {
            clase.setInicio(inicio);
        }

        if (fin != null) {
            clase.setFin(fin);
        }
    }
}
