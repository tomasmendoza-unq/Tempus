package edu.ar.tempus.controller.dto.carrera;

import edu.ar.tempus.controller.dto.materia.MateriaDTOResponse;
import edu.ar.tempus.controller.dto.materia.MateriaDTOResponseSimple;
import edu.ar.tempus.model.Carrera;
import java.util.List;

public record CarreraDTOResponse(Long idCarrera ,String nombreCarrera, List<MateriaDTOResponseSimple>materias) {
    public static CarreraDTOResponse desdeModelo(Carrera carrera) {
        List<MateriaDTOResponseSimple> materiaDTOResponses = carrera.getMaterias().stream().map(MateriaDTOResponseSimple::desdeModelo).toList();
        return new CarreraDTOResponse(carrera.getId(), carrera.getNombreCarrera(), materiaDTOResponses);
    }
}
