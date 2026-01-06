package edu.ar.tempus.controller.dto.materia;

import edu.ar.tempus.model.Materia;

public record MateriaDTOResponseSimple(Long materiaId,
                                       String materiaNombre) {
    public static MateriaDTOResponseSimple desdeModelo(Materia materia) {
        return new MateriaDTOResponseSimple(materia.getMateriaId(), materia.getMateriaNombre());
    }
}
