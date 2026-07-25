package edu.ar.tempus.feature.alumno.controller;

import edu.ar.tempus.controller.dto.carrera.CarreraDTOResponseSimple;
import edu.ar.tempus.controller.dto.materia.MateriaDTOResponseSimple;
import edu.ar.tempus.feature.alumno.annotations.AlumnoEndpoints;
import edu.ar.tempus.feature.alumno.controller.dto.AlumnoResponseDetallesDTO;
import edu.ar.tempus.controller.exceptions.ErrorResponseDTO;
import edu.ar.tempus.feature.alumno.model.Alumno;
import edu.ar.tempus.feature.alumno.service.AlumnoService;
import edu.ar.tempus.feature.carrera.controller.dto.SuscripcionCarreraRequestDTO;
import edu.ar.tempus.model.Carrera;
import edu.ar.tempus.model.Materia;
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


    private final AlumnoService alumnoService;

    public AlumnoControllerREST( AlumnoService alumnoService) {
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
                    schema = @Schema(implementation = AlumnoResponseDetallesDTO.class)
            )
    )
   @AlumnoEndpoints
    @ApiResponse(
            responseCode = "404",
            description = "No se encontró el usuario autenticado.",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ErrorResponseDTO.class)
            )
    )
    public ResponseEntity<AlumnoResponseDetallesDTO> getPerfil(
            @RequestAttribute("userId") Long idAlumno
    ) {

        Alumno alumno = alumnoService.getAlumnoById(idAlumno);

        AlumnoResponseDetallesDTO response = AlumnoResponseDetallesDTO.desdeModelo(alumno);

        return ResponseEntity.ok(response);
    }

    @PostMapping("/anotarse/{comisionesId}")
    @AlumnoEndpoints
    public ResponseEntity<String> anotarseAComisiones(@PathVariable("comisionesId") List<Long> comisionId,
                                                      @RequestAttribute("userId") Long idAlumno) {
        alumnoService.anotarseAComision(comisionId, idAlumno); //aca se podria generar un certificado

        return ResponseEntity.ok("Se realizo con exito la operacion");
    }

    @PostMapping("/aprobar/{comisionesId}")
    @AlumnoEndpoints
    public ResponseEntity<String> AprobarAMateria(@PathVariable("comisionesId") List<Long> comisionId,
                                                  @RequestAttribute("userId") Long idAlumno) {
        alumnoService.aprobarMaterias(comisionId, idAlumno);

        return ResponseEntity.ok("Se realizo con exito la operacion");
    }

    @PostMapping("/desaprobar/{materiaId}")
    @AlumnoEndpoints
    public ResponseEntity<String> DesaprobarMateria(@PathVariable("materiaId") Long materiaId,
                                                    @RequestAttribute("userId") Long idAlumno) {
        alumnoService.desaprobarMateria(materiaId, idAlumno);

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
    @AlumnoEndpoints
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
    @AlumnoEndpoints
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
    @AlumnoEndpoints
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

    @GetMapping("/disponible")
    @AlumnoEndpoints
    public ResponseEntity<List<MateriaDTOResponseSimple>> getDisponibleMateria(@RequestAttribute("userId") Long idAlumno){
        List<Materia> materias = alumnoService.recuperarMateriasDisponibles(idAlumno);

        List<MateriaDTOResponseSimple> response = materias.stream().map(MateriaDTOResponseSimple::desdeModelo).toList();

        return ResponseEntity.ok(response);
    }


    @PutMapping("/carreras/{carreraId}/activar")
    @AlumnoEndpoints
    public ResponseEntity<String> seleccionarCarrera(@PathVariable Long carreraId,
                                                     @RequestAttribute("userId") Long idAlumno) {
        alumnoService.seleccionarCarreraActiva(
                carreraId,
                idAlumno
        );

        return ResponseEntity.ok("Carrera actualizada");
    }
}
