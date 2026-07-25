package edu.ar.tempus.feature.alumno.controller.dto;

import edu.ar.tempus.controller.dto.carrera.CarreraDTOResponseSimple;
import edu.ar.tempus.feature.alumno.model.Alumno;

import java.util.List;

public record AlumnoResponseSimpleDTO(
        String email,
        String nombre,
        CarreraDTOResponseSimple carreraActiva,
        List<CarreraDTOResponseSimple> carreras
) {

    public static AlumnoResponseSimpleDTO desdeModelo(Alumno alumno) {
        return new AlumnoResponseSimpleDTO(
                alumno.getEmail(),
                alumno.getNombre(),
                alumno.getCarreraActiva() != null
                        ? CarreraDTOResponseSimple.desdeModelo(alumno.getCarreraActiva())
                        : null,
                alumno.getCarreras().stream().map(CarreraDTOResponseSimple::desdeModelo).toList()
        );
    }
}
