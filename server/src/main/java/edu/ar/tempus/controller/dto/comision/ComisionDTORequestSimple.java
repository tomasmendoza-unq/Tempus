package edu.ar.tempus.controller.dto.comision;

import edu.ar.tempus.controller.dto.claseHorario.ClaseHorarioDTORequest;
import edu.ar.tempus.model.Comision;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record ComisionDTORequestSimple(
        @NotNull(message = "Los horarios son requeridos")
        List<ClaseHorarioDTORequest> claseHorario,

        @NotNull(message = "El nombre de la comision es requerida")
        String nombreComision
) {
    public Comision aModelo() {
        return Comision.builder()
                .clases(claseHorario.stream().map(ClaseHorarioDTORequest::aModelo).toList())
                .comisionNombre(nombreComision)
                .build();
    }
}
