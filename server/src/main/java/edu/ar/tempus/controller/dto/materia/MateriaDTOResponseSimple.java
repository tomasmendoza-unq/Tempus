package edu.ar.tempus.controller.dto.materia;

import edu.ar.tempus.controller.dto.comision.ComisionDTOResponse;
import edu.ar.tempus.model.Materia;

import java.util.List;
import java.util.stream.Collectors;

public record MateriaDTOResponseSimple(Long materiaId,
                                       String materiaNombre
) {
    public static MateriaDTOResponseSimple desdeModelo(Materia materia) {
        return new MateriaDTOResponseSimple(materia.getMateriaId(), materia.getMateriaNombre());
    }
}
