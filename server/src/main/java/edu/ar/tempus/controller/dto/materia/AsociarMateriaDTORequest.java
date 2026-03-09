package edu.ar.tempus.controller.dto.materia;

import java.util.List;

public record AsociarMateriaDTORequest(
        Long materiaOrigenId,
        List<Long> materiasDestinoIds
) {
}
