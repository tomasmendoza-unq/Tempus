package edu.ar.tempus.controller.dto.carrera;

import edu.ar.tempus.controller.dto.materia.MateriaComisionDTORequest;
import edu.ar.tempus.model.Carrera;


import java.util.List;

public record CarreraDTOBulkRequest(
        String nombreCarrera,
        List<MateriaComisionDTORequest> materias
) {

    public Carrera aModelo() {
        return Carrera.builder()
                .nombreCarrera(nombreCarrera)
                .materias(materias.stream().map(MateriaComisionDTORequest::aModelo).toList())
                .build();
    }
}
