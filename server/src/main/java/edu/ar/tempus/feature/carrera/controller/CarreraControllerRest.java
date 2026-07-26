package edu.ar.tempus.feature.carrera.controller;

import edu.ar.tempus.controller.dto.carrera.CarreraDTOBulkRequest;
import edu.ar.tempus.controller.dto.carrera.CarreraDTORequest;
import edu.ar.tempus.controller.dto.carrera.CarreraDTOResponse;
import edu.ar.tempus.controller.dto.carrera.CarreraDTOResponseSimple;
import edu.ar.tempus.feature.alumno.annotations.AlumnoEndpoints;
import edu.ar.tempus.feature.alumno.service.AlumnoService;
import edu.ar.tempus.model.Carrera;
import edu.ar.tempus.service.CarreraService;
import edu.ar.tempus.utils.AuthUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/carrera")
public class CarreraControllerRest {

    private final CarreraService carreraService;

    private final AlumnoService alumnoService;

    public CarreraControllerRest(CarreraService carreraService, AlumnoService alumnoService) {
        this.carreraService = carreraService;
        this.alumnoService = alumnoService;
    }

    @GetMapping
    public ResponseEntity<List<CarreraDTOResponse>> obtenerCarreras() {
        List<Carrera> carreras = carreraService.recuperarTodos();
        List<CarreraDTOResponse> response = carreras.stream().map(CarreraDTOResponse::desdeModelo).toList();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/buscar/{nombreCarrera}")
    public ResponseEntity<List<CarreraDTOResponse>> obtenerCarreras(@PathVariable String nombreCarrera) {
        List<Carrera> carreras = carreraService.recuperarCarreraPorNombre(nombreCarrera);
        List<CarreraDTOResponse> response = carreras.stream().map(CarreraDTOResponse::desdeModelo).toList();
        return ResponseEntity.status(HttpStatus.FOUND).body(response);
    }

    @GetMapping("/{idCarrera}")
    public ResponseEntity<CarreraDTOResponse>  obtenerCarreraPorIdCarrera(@PathVariable Long idCarrera) {
        Carrera carrera = carreraService.recuperar(idCarrera);
        return ResponseEntity.status(HttpStatus.FOUND).body(CarreraDTOResponse.desdeModelo(carrera));
    }


    @GetMapping("/disponibles")
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

    @PostMapping("/load")
    public ResponseEntity<String> loadPorBulk(@RequestBody CarreraDTOBulkRequest carreraDTOBulkRequest) {
        carreraService.guardarCarreraCompleta(carreraDTOBulkRequest.aModelo());
        return ResponseEntity.ok("Se cargo con exito la oferta");
    }

    @PostMapping("/crear")
    public ResponseEntity<CarreraDTOResponse>  crear(@RequestBody CarreraDTORequest carreraDTORequest) {
        Carrera carrera = CarreraDTORequest.aModelo(carreraDTORequest);

        Carrera carreraGuardada = carreraService.guardar(carrera, carreraDTORequest.idsMaterias());

        return ResponseEntity.status(HttpStatus.CREATED).body(CarreraDTOResponse.desdeModelo(carreraGuardada));
    }
}
