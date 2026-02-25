package edu.ar.tempus.controller.dto.comision;

import edu.ar.tempus.controller.dto.claseHorario.ClaseHorarioDTOResponse;
import edu.ar.tempus.controller.dto.materia.MateriaDTOResponse;
import edu.ar.tempus.controller.dto.materia.MateriaDTOResponseSimple;
import edu.ar.tempus.model.ClaseHorario;
import edu.ar.tempus.model.Comision;
import edu.ar.tempus.model.Materia;
import org.jspecify.annotations.Nullable;

import java.time.LocalTime;
import java.util.List;

public record ComisionDTOResponse(
        Long comisionId,
        List<ClaseHorarioDTOResponse> claseHorario,
        MateriaDTOResponseSimple materia
) {
    public static ComisionDTOResponse desdeModelo(Comision comision) {
        return new ComisionDTOResponse(
                comision.getComisionId(),
                comision.getClases().stream().map(ClaseHorarioDTOResponse::desdeModelo).toList(),
                MateriaDTOResponseSimple.desdeModelo(comision.getMateria())
        );
    }
}
