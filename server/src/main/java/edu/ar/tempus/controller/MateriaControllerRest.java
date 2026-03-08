package edu.ar.tempus.controller;

import edu.ar.tempus.controller.dto.materia.MateriaDTORequest;
import edu.ar.tempus.controller.dto.materia.MateriaDTOResponse;
import edu.ar.tempus.controller.dto.materia.MateriaDTOResponseSimple;
import edu.ar.tempus.model.Materia;

import edu.ar.tempus.service.MateriaService;
import edu.ar.tempus.utils.AuthUtils;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/materia")
public final class MateriaControllerRest {

    private final MateriaService materiaService;
    private final AuthUtils authUtils;

    public MateriaControllerRest(MateriaService materiaService, AuthUtils authUtils) {
        this.materiaService = materiaService;
        this.authUtils = authUtils;
    }

    @GetMapping
    public ResponseEntity<List<MateriaDTOResponseSimple>> getAllMateria(){
        List<Materia> materias = materiaService.recuperarTodos();
        List<MateriaDTOResponseSimple> response = materias.stream().map(MateriaDTOResponseSimple::desdeModelo).toList();

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MateriaDTOResponse> getMateria(@PathVariable Long id) {
        Materia materia = materiaService.recuperar(id);

        return ResponseEntity.ok(MateriaDTOResponse.desdeModelo(materia));
    }

    @GetMapping("/disponible")
    public ResponseEntity<List<MateriaDTOResponseSimple>> getDisponibleMateria(Authentication authentication){

        Long alumnoId = authUtils.getAlumnoId(authentication);

        List<Materia> materias = materiaService.recuperarMateriasDisponibles(alumnoId);

        List<MateriaDTOResponseSimple> response = materias.stream().map(MateriaDTOResponseSimple::desdeModelo).toList();

        return ResponseEntity.ok(response);
    }

    @GetMapping("/buscar/{nombreMateria}")
    public ResponseEntity<List<MateriaDTOResponseSimple>> buscarMaterias(@PathVariable String nombreMateria){
        List<Materia> materias = materiaService.recuperarMateriasPorNombre(nombreMateria);

        List<MateriaDTOResponseSimple> response = materias.stream().map(MateriaDTOResponseSimple::desdeModelo).toList();

        return ResponseEntity.ok(response);
    }

    @PostMapping("/crear")
    public ResponseEntity<MateriaDTOResponse> crearMateria(@RequestBody MateriaDTORequest materiaDTO) {
        Materia materia = MateriaDTORequest.aModelo(materiaDTO);

        Materia materiaGuardada = materiaService.guardar(materia);

        return ResponseEntity.status(HttpStatus.CREATED).body(MateriaDTOResponse.desdeModelo(materiaGuardada));
    }

    @PostMapping("/asociar/{materiaOrigenId}/{materiaDestinoId}")
    public ResponseEntity<Void> asociarMaterias(@PathVariable Long materiaOrigenId,
                                                @PathVariable Long materiaDestinoId){
        materiaService.asociarMateria(materiaOrigenId, materiaDestinoId);
        return ResponseEntity.ok().build();
    }

}