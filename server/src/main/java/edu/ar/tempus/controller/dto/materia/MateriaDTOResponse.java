package edu.ar.tempus.controller.dto.materia;

import edu.ar.tempus.controller.dto.comision.ComisionDTOResponse;
import edu.ar.tempus.controller.dto.comision.ComisionDTOResponseSimple;
import edu.ar.tempus.model.Materia;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


public record MateriaDTOResponse(
        Long materiaId,
        String materiaNombre,
        Set<MateriaDTOResponse> correlativas,
        List<ComisionDTOResponseSimple> comisiones

) {
    public static MateriaDTOResponse desdeModelo(Materia materia) {
        Set<MateriaDTOResponse> correlativas = materia.getCorrelativas().stream().map(MateriaDTOResponse::desdeModelo).collect(Collectors.toSet());
        List<ComisionDTOResponseSimple> comisiones = materia.getComisiones().stream().map(ComisionDTOResponseSimple::desdeModelo).collect(Collectors.toList());


        return new MateriaDTOResponse(materia.getMateriaId(), materia.getMateriaNombre(), correlativas,  comisiones);
    }
}