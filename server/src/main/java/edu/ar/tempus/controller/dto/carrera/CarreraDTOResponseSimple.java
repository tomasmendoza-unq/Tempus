package edu.ar.tempus.controller.dto.carrera;

import edu.ar.tempus.model.Carrera;

public record CarreraDTOResponseSimple(
        Long idCarrera,
        String nombreCarrera
) {
    public static CarreraDTOResponseSimple desdeModelo(Carrera carrera){
        return new CarreraDTOResponseSimple(
                carrera.getId(),
                carrera.getNombreCarrera()
        );
    }
}
