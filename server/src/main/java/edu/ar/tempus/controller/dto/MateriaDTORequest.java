package edu.ar.tempus.controller.dto;

import edu.ar.tempus.model.Materia;

public record MateriaDTORequest(
        String nombre
) {


    public static Materia aModelo(MateriaDTORequest materiaDTO) {
        return Materia.builder().materiaNombre(materiaDTO.nombre()).build();
    }
}
