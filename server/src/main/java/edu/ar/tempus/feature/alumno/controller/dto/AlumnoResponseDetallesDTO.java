package edu.ar.tempus.feature.alumno.controller.dto;

import edu.ar.tempus.controller.dto.carrera.CarreraDTOResponse;
import edu.ar.tempus.controller.dto.carrera.CarreraDTOResponseSimple;
import edu.ar.tempus.controller.dto.comision.ComisionDTOResponseSimple;
import edu.ar.tempus.controller.dto.materia.MateriaDTOResponseSimple;
import edu.ar.tempus.feature.alumno.model.Alumno;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

@Schema(
        name = "UsuarioResponseDetallesDTO",
        description = "Información detallada del perfil del usuario autenticado."
)
public record AlumnoResponseDetallesDTO(

        @Schema(
                description = "Identificador único del usuario.",
                example = "1"
        )
        Long id,

        @Schema(
                description = "Correo electrónico del usuario.",
                example = "juan.perez@alumnos.unq.edu.ar"
        )
        String email,

        @Schema(
                description = "Nombre del usuario.",
                example = "Juan"
        )
        String nombre,

        @Schema(
                description = "Apellido del usuario.",
                example = "Pérez"
        )
        String apellido,

        @Schema(
                description = "Comisiones en las que se encuentra inscripto el usuario."
        )
        List<ComisionDTOResponseSimple> comisiones,

        @Schema(
                description = "Materias aprobadas por el usuario."
        )
        List<MateriaDTOResponseSimple> cursadas,

        @Schema(
                description = "Carreras a las que pertenece el usuario."
        )
        List<CarreraDTOResponse> carreras,

        @Schema(
                description = "Carrera actualmente seleccionada por el usuario."
        )
        CarreraDTOResponseSimple carreraActiva

) {
    public static AlumnoResponseDetallesDTO desdeModelo(Alumno alumno) {
        return new AlumnoResponseDetallesDTO(
                alumno.getId(),
                alumno.getEmail(),
                alumno.getNombre(),
                alumno.getApellido(),
                alumno.getComisiones().stream().map(ComisionDTOResponseSimple::desdeModelo).toList(),
                alumno.getMateriasAprobadas().stream().map(MateriaDTOResponseSimple::desdeModelo).toList(),
                alumno.getCarreras().stream().map(CarreraDTOResponse::desdeModelo).toList(),
                alumno.getCarreraActiva() != null
                        ? CarreraDTOResponseSimple.desdeModelo(alumno.getCarreraActiva())
                        : null
        );
    }
}