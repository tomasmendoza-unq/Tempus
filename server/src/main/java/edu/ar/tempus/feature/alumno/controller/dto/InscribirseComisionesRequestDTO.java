package edu.ar.tempus.feature.alumno.controller.dto;

import java.util.List;

public record InscribirseComisionesRequestDTO(
        List<Long> comisionesId
) {
}
