package edu.ar.tempus.controller.dto.comision;

import edu.ar.tempus.controller.dto.claseHorario.ClaseHorarioDTORequest;
import edu.ar.tempus.controller.dto.claseHorario.ClaseHorarioDTOResponse;
import edu.ar.tempus.model.ClaseHorario;
import edu.ar.tempus.model.Comision;
import edu.ar.tempus.model.Materia;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotNull;

import java.time.LocalTime;
import java.util.List;

public record ComisionDTORequest(
        @NotNull(message = "Los horarios son requeridos")
        List<ClaseHorarioDTORequest> claseHorario,

        @NotNull(message = "La materia es requerida")
        Long materiaId
) {



    public static Comision aModelo(ComisionDTORequest request) {
        List<ClaseHorario> claseHorario = request.claseHorario.stream().map(ClaseHorarioDTORequest::aModelo).toList();

        return Comision.builder()
                .clases(claseHorario)
                .build();
    }
}
