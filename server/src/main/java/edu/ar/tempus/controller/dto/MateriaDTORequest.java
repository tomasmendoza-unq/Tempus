package edu.ar.tempus.controller.dto;

import edu.ar.tempus.model.Materia;

import java.util.Set;
import java.util.stream.Collectors;

public record MateriaDTORequest(
        String materiaNombre,
        Set<MateriaDTORequest> correlativas
) {


    public static Materia aModelo(MateriaDTORequest materiaDTO) {
        Set<Materia> correlativas = materiaDTO.correlativas.stream().map(MateriaDTORequest::aModeloSimple).collect(Collectors.toSet());
        Materia materia = aModeloSimple(materiaDTO);
        materia.agregarCorrelativas(correlativas);
        return materia;
    }

    private static Materia aModeloSimple(MateriaDTORequest materiaDTO) {
        return Materia.builder().materiaNombre(materiaDTO.materiaNombre()).build();
    }
}
