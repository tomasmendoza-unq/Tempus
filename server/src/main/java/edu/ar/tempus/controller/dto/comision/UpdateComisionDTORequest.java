package edu.ar.tempus.controller.dto.comision;

import edu.ar.tempus.model.Comision;
import edu.ar.tempus.model.Materia;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record UpdateComisionDTORequest(
        @NotNull
        @Min(1)
        Long materiaId
) {

    public void actualizar(Comision comision, Materia materia) {
        comision.setMateria(materia);
    }
}