package edu.ar.tempus.controller.dto.materia;

import edu.ar.tempus.controller.dto.comision.ComisionPreviewDTO;
import edu.ar.tempus.model.Materia;

import java.util.List;

public record MateriaPreviewDTO(
        String materiaNombre,
        List<ComisionPreviewDTO> comisiones
) {
    public static MateriaPreviewDTO desdeModelo(Materia materia) {
        return new MateriaPreviewDTO(
                materia.getMateriaNombre(),
                materia.getComisiones().stream().map(ComisionPreviewDTO::desdeModelo).toList()
        );
    }
}