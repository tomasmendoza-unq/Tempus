package edu.ar.tempus.controller.dto;

import edu.ar.tempus.model.Materia;

public record MateriaDTOResponse(
        Long materiaId,
        String materiaNombre
) {
    public static MateriaDTOResponse desdeModelo(Materia materia) {
        return new MateriaDTOResponse(materia.getMateriaId(), materia.getMateriaNombre());
    }
}
