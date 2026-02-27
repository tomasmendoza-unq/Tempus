package edu.ar.tempus.controller.dto.carrera;

import edu.ar.tempus.model.Carrera;
import java.util.Set;

public record CarreraDTORequest(String nombreCarrera,
                                Set<Long> idsMaterias) {
    public static Carrera aModelo(CarreraDTORequest carreraDTORequest) {
        return Carrera.builder().nombreCarrera(carreraDTORequest.nombreCarrera()).build();
    }
}
