package edu.ar.tempus.controller.dto.carrera;

import edu.ar.tempus.controller.dto.materia.MateriaComisionDTORequest;


import java.util.List;

public record CarreraDTOBulkRequest(
        String nombreCarrera,
        List<MateriaComisionDTORequest> materias
) {

}
