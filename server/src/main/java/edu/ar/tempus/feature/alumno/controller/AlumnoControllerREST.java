package edu.ar.tempus.feature.alumno.controller;

import edu.ar.tempus.controller.dto.carrera.CarreraDTOResponseSimple;
import edu.ar.tempus.controller.dto.usuario.UsuarioResponseDetallesDTO;
import edu.ar.tempus.controller.dto.usuario.UsuarioResponseSimpleDTO;
import edu.ar.tempus.controller.exceptions.ErrorResponseDTO;
import edu.ar.tempus.feature.alumno.service.AlumnoService;
import edu.ar.tempus.feature.carrera.controller.dto.SuscripcionCarreraRequestDTO;
import edu.ar.tempus.model.Carrera;
import edu.ar.tempus.model.Usuario;
import edu.ar.tempus.service.UsuarioService;
import edu.ar.tempus.utils.AuthUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/alumno")
public final class AlumnoControllerREST {

    private final AuthUtils authUtils;

    private final UsuarioService usuarioService;

    private final AlumnoService alumnoService;

    public AlumnoControllerREST(AuthUtils authUtils, UsuarioService usuarioService, AlumnoService alumnoService) {
        this.authUtils = authUtils;
        this.usuarioService = usuarioService;
        this.alumnoService = alumnoService;
    }

    @GetMapping
    @Operation(
            summary = "Obtener perfil del alumno autenticado",
            description = "Retorna la información completa del perfil del alumno autenticado, incluyendo sus datos personales, carreras, carrera activa, materias aprobadas y comisiones."
    )
    @ApiResponse(
            responseCode = "200",
            description = "Perfil obtenido correctamente.",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = UsuarioResponseDetallesDTO.class)
            )
    )
    @ApiResponse(
            responseCode = "401",
            description = "El usuario no está autenticado o el token es inválido.",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ErrorResponseDTO.class)
            )
    )
    @ApiResponse(
            responseCode = "404",
            description = "No se encontró el usuario autenticado.",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ErrorResponseDTO.class)
            )
    )
    public ResponseEntity<UsuarioResponseDetallesDTO> getPerfil(
            @RequestAttribute("userId") Long idAlumno
    ) {

        Usuario usuario = usuarioService.recuperarUsuarioPorId(idAlumno);

        UsuarioResponseDetallesDTO response = UsuarioResponseDetallesDTO.desdeModelo(usuario);

        return ResponseEntity.ok(response);
    }

    @PostMapping("/anotarse/{comisionesId}")
    public ResponseEntity<String> anotarseAComisiones(@PathVariable("comisionesId") List<Long> comisionId, Authentication authentication) {
        usuarioService.anotarseAComision(comisionId, authUtils.getAlumnoId(authentication)); //aca se podria generar un certificado

        return ResponseEntity.ok("Se realizo con exito la operacion");
    }

    @PostMapping("/aprobar/{comisionesId}")
    public ResponseEntity<String> AprobarAMateria(@PathVariable("comisionesId") List<Long> comisionId, Authentication authentication) {
        usuarioService.aprobarMaterias(comisionId, authUtils.getAlumnoId(authentication));

        return ResponseEntity.ok("Se realizo con exito la operacion");
    }

    @PostMapping("/desaprobar/{materiaId}")
    public ResponseEntity<String> DesaprobarMateria(@PathVariable("materiaId") Long materiaId, Authentication authentication) {
        usuarioService.desaprobarMateria(materiaId, authUtils.getAlumnoId(authentication));

        return ResponseEntity.ok("Se realizo con exito la operacion");
    }

    @PostMapping("/carreras/suscribirse")
    @Operation(
            summary = "Suscribirse a una carrera",
            description = "Permite al usuario autenticado suscribirse a una carrera disponible."
    )
    @ApiResponse(
            responseCode = "200",
            description = "Suscripción realizada correctamente.",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = CarreraDTOResponseSimple.class)
            )
    )
    @ApiResponse(
            responseCode = "400",
            description = "La solicitud es inválida o el usuario ya se encuentra suscripto a la carrera.",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ErrorResponseDTO.class)
            )
    )
    @ApiResponse(
            responseCode = "401",
            description = "El usuario no está autenticado o el token es inválido.",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ErrorResponseDTO.class)
            )
    )
    @ApiResponse(
            responseCode = "404",
            description = "No se encontró la carrera solicitada.",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ErrorResponseDTO.class)
            )
    )
    public ResponseEntity<CarreraDTOResponseSimple> suscribirseACarrera(
            @Valid @RequestBody SuscripcionCarreraRequestDTO request,
            @RequestAttribute("userId") Long idAlumno) {

        Carrera carrera = alumnoService.suscribirseACarrera(request.idCarrera(), idAlumno);

        return ResponseEntity.ok(CarreraDTOResponseSimple.desdeModelo(carrera));
    }

    @PostMapping("/carreras/desuscribirse")
    @Operation(
            summary = "Desuscribirse de una carrera",
            description = "Permite al usuario autenticado cancelar su suscripción a una carrera."
    )
    @ApiResponse(
            responseCode = "200",
            description = "Desuscripción realizada correctamente.",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = CarreraDTOResponseSimple.class)
            )
    )
    @ApiResponse(
            responseCode = "400",
            description = "La solicitud es inválida o el usuario no está suscripto a la carrera.",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ErrorResponseDTO.class)
            )
    )
    @ApiResponse(
            responseCode = "401",
            description = "El usuario no está autenticado o el token es inválido.",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ErrorResponseDTO.class)
            )
    )
    @ApiResponse(
            responseCode = "404",
            description = "No se encontró la carrera solicitada.",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ErrorResponseDTO.class)
            )
    )
    public ResponseEntity<CarreraDTOResponseSimple> desuscribirseACarrera(
            @Valid @RequestBody SuscripcionCarreraRequestDTO request,
            @RequestAttribute("userId") Long idAlumno) {

        Carrera carrera = alumnoService.desuscribirseACarrera(request.idCarrera(), idAlumno);

        return ResponseEntity.ok(CarreraDTOResponseSimple.desdeModelo(carrera));
    }
    @GetMapping("/perfil")
    public ResponseEntity<UsuarioResponseSimpleDTO>  obtenerDetallesSimple(Authentication authentication) {
        Long alumnoId = authUtils.getAlumnoId(authentication);

        Usuario usuario = usuarioService.recuperarUsuarioPorId(alumnoId);

        UsuarioResponseSimpleDTO response = UsuarioResponseSimpleDTO.desdeModelo(usuario);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/carreras/disponibles")
    @Operation(
            summary = "Obtener carreras disponibles",
            description = "Retorna la lista de carreras en las que el alumno autenticado aún puede inscribirse."
    )
    @ApiResponse(
            responseCode = "200",
            description = "Carreras disponibles obtenidas correctamente.",
            content = @Content(
                    mediaType = "application/json",
                    array = @ArraySchema(
                            schema = @Schema(implementation = CarreraDTOResponseSimple.class)
                    )
            )
    )
    @ApiResponse(
            responseCode = "401",
            description = "El usuario no está autenticado o el token es inválido.",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ErrorResponseDTO.class)
            )
    )
    public ResponseEntity<List<CarreraDTOResponseSimple>> getCarrerasDisponibles(
            @RequestAttribute("userId") Long idAlumno
    ) {
        List<Carrera> carreras = alumnoService.getCarrerasDisponibles(idAlumno);

        return ResponseEntity.ok(
                carreras.stream()
                        .map(CarreraDTOResponseSimple::desdeModelo)
                        .toList()
        );
    }

    @PutMapping("/carreras/{carreraId}/activar")
    public ResponseEntity<String> seleccionarCarrera(@PathVariable Long carreraId, Authentication authentication) {
        usuarioService.seleccionarCarreraActiva(
                carreraId,
                authUtils.getAlumnoId(authentication)
        );

        return ResponseEntity.ok("Carrera actualizada");
    }
}
