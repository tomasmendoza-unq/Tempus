package edu.ar.tempus.controller.dto.materia;

import edu.ar.tempus.model.Materia;

import java.util.Set;
import java.util.stream.Collectors;


public record MateriaDTOResponse(
        Long materiaId,
        String materiaNombre,
        Set<MateriaDTOResponse> correlativas

) {
    public static MateriaDTOResponse desdeModelo(Materia materia) {
        Set<MateriaDTOResponse> correlativas = materia.getCorrelativas().stream().map(MateriaDTOResponse::desdeModelo).collect(Collectors.toSet());

        return new MateriaDTOResponse(materia.getMateriaId(), materia.getMateriaNombre(), correlativas);
    }
}