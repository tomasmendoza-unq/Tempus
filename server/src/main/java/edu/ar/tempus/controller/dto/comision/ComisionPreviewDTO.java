package edu.ar.tempus.controller.dto.comision;

import edu.ar.tempus.controller.dto.claseHorario.ClaseHorarioPreviewDTO;
import edu.ar.tempus.model.Comision;

import java.util.List;

public record ComisionPreviewDTO(
        String comisionNombre,
        String modalidad,
        List<ClaseHorarioPreviewDTO> claseHorario
) {
    public static ComisionPreviewDTO desdeModelo(Comision comision) {
        return new ComisionPreviewDTO(
                comision.getComisionNombre(),
                comision.getModalidad(),
                comision.getClases().stream().map(ClaseHorarioPreviewDTO::desdeModelo).toList()
        );
    }
}
