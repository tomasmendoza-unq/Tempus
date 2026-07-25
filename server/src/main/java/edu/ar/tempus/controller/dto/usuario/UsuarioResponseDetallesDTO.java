package edu.ar.tempus.controller.dto.usuario;

import edu.ar.tempus.controller.dto.carrera.CarreraDTOResponse;
import edu.ar.tempus.controller.dto.carrera.CarreraDTOResponseSimple;
import edu.ar.tempus.controller.dto.comision.ComisionDTOResponseSimple;
import edu.ar.tempus.controller.dto.materia.MateriaDTOResponseSimple;
import edu.ar.tempus.model.Usuario;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

@Schema(
        name = "UsuarioResponseDetallesDTO",
        description = "Información detallada del perfil del usuario autenticado."
)
public record UsuarioResponseDetallesDTO(

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
        List<MateriaDTOResponseSimple> materiaDTOResponseSimples,

        @Schema(
                description = "Carreras a las que pertenece el usuario."
        )
        List<CarreraDTOResponse> carreras,

        @Schema(
                description = "Carrera actualmente seleccionada por el usuario."
        )
        CarreraDTOResponseSimple carreraActiva

) {
    public static UsuarioResponseDetallesDTO desdeModelo(Usuario usuario) {
        return new UsuarioResponseDetallesDTO(
                usuario.getId(),
                usuario.getEmail(),
                usuario.getNombre(),
                usuario.getApellido(),
                usuario.getComisiones().stream().map(ComisionDTOResponseSimple::desdeModelo).toList(),
                usuario.getMateriasAprobadas().stream().map(MateriaDTOResponseSimple::desdeModelo).toList(),
                usuario.getCarreras().stream().map(CarreraDTOResponse::desdeModelo).toList(),
                usuario.getCarreraActiva() != null
                        ? CarreraDTOResponseSimple.desdeModelo(usuario.getCarreraActiva())
                        : null
        );
    }
}