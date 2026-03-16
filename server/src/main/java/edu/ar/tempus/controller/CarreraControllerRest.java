package edu.ar.tempus.controller;

import edu.ar.tempus.controller.dto.carrera.CarreraDTOBulkRequest;
import edu.ar.tempus.controller.dto.carrera.CarreraDTORequest;
import edu.ar.tempus.controller.dto.carrera.CarreraDTOResponse;
import edu.ar.tempus.controller.dto.carrera.CarreraDTOResponseSimple;
import edu.ar.tempus.model.Carrera;
import edu.ar.tempus.model.Usuario;
import edu.ar.tempus.service.CarreraService;
import edu.ar.tempus.utils.AuthUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/carrera")
public final class CarreraControllerRest {

    private final CarreraService carreraService;


    private final AuthUtils authUtils;


    public CarreraControllerRest(CarreraService carreraService, AuthUtils authUtils) {
        this.carreraService = carreraService;
        this.authUtils = authUtils;
    }

    @GetMapping("/public")
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
    public ResponseEntity<List<CarreraDTOResponseSimple>> obtenerCarrerasDisponibles(Authentication authentication) {
        Long alumnoId = authUtils.getAlumnoId(authentication);

        List<Carrera> carreras = carreraService.recuperarCarrerasPorAlumno(alumnoId);

        List<CarreraDTOResponseSimple> response = carreras.stream().map(CarreraDTOResponseSimple::desdeModelo).toList();

        return ResponseEntity.ok(response);
    }

    @PostMapping("/load")
    public ResponseEntity<String> loadPorBulk(@RequestBody CarreraDTOBulkRequest carreraDTOBulkRequest) {
        carreraService.guardarCarreraCompleta(carreraDTOBulkRequest);
        return ResponseEntity.ok("Se cargo con exito la oferta");
    }

    @PostMapping("/crear")
    public ResponseEntity<CarreraDTOResponse>  crear(@RequestBody CarreraDTORequest carreraDTORequest) {
        Carrera carrera = CarreraDTORequest.aModelo(carreraDTORequest);

        Carrera carreraGuardada = carreraService.guardar(carrera, carreraDTORequest.idsMaterias());

        return ResponseEntity.status(HttpStatus.CREATED).body(CarreraDTOResponse.desdeModelo(carreraGuardada));
    }
}
