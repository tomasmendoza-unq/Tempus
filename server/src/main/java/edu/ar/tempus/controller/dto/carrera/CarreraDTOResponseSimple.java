package edu.ar.tempus.controller.dto.carrera;

import edu.ar.tempus.model.Carrera;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(
        name = "CarreraDTOResponseSimple",
        description = "Información resumida de una carrera."
)
public record CarreraDTOResponseSimple(

        @Schema(
                description = "Identificador único de la carrera.",
                example = "3"
        )
        Long idCarrera,

        @Schema(
                description = "Nombre de la carrera.",
                example = "Licenciatura en Informática"
        )
        String nombreCarrera

) {
    public static CarreraDTOResponseSimple desdeModelo(Carrera carrera){
        return new CarreraDTOResponseSimple(
                carrera.getId(),
                carrera.getNombreCarrera()
        );
    }
}