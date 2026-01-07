package edu.ar.tempus.controller;

import edu.ar.tempus.controller.dto.carrera.CarreraDTORequest;
import edu.ar.tempus.controller.dto.carrera.CarreraDTOResponse;
import edu.ar.tempus.model.Carrera;
import edu.ar.tempus.service.CarreraService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/carrera")
public final class CarreraControllerRest {

    private final CarreraService carreraService;

    public CarreraControllerRest(CarreraService carreraService) {
        this.carreraService = carreraService;
    }

    @GetMapping
    public ResponseEntity<List<CarreraDTOResponse>> obtenerCarreras() {
        List<Carrera> carreras = carreraService.recuperarTodos();
        List<CarreraDTOResponse> response = carreras.stream().map(CarreraDTOResponse::desdeModelo).toList();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{idCarrera}")
    public ResponseEntity<CarreraDTOResponse>  obtenerCarreraPorIdCarrera(@PathVariable Long idCarrera) {
        Carrera carrera = carreraService.recuperar(idCarrera);
        return ResponseEntity.status(HttpStatus.FOUND).body(CarreraDTOResponse.desdeModelo(carrera));
    }

    @PostMapping("/crear")
    public ResponseEntity<CarreraDTOResponse>  crear(@RequestBody CarreraDTORequest carreraDTORequest) {
        Carrera carrera = CarreraDTORequest.aModelo(carreraDTORequest);

        Carrera carreraGuardada = carreraService.guardar(carrera, carreraDTORequest.idsMaterias());

        return ResponseEntity.status(HttpStatus.CREATED).body(CarreraDTOResponse.desdeModelo(carreraGuardada));
    }
}
