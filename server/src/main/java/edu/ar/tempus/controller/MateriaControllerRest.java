package edu.ar.tempus.controller;

import edu.ar.tempus.controller.dto.MateriaDTORequest;
import edu.ar.tempus.controller.dto.MateriaDTOResponse;
import edu.ar.tempus.model.Materia;
import edu.ar.tempus.service.MateriaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/materia")
public final class MateriaControllerRest {

    private final MateriaService materiaService;

    public MateriaControllerRest(MateriaService materiaService) {
        this.materiaService = materiaService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<MateriaDTOResponse> getMateria(@PathVariable Long id) {
        Materia materia = materiaService.recuperar(id);

        return ResponseEntity.status(HttpStatus.FOUND).body(MateriaDTOResponse.desdeModelo(materia));
    }

    @PostMapping("/crear")
    public ResponseEntity<MateriaDTOResponse> crearMateria(@RequestBody MateriaDTORequest materiaDTO) {
        Materia materia = MateriaDTORequest.aModelo(materiaDTO);

        Materia materiaGuardada = materiaService.guardar(materia);

        return ResponseEntity.ok(MateriaDTOResponse.desdeModelo(materiaGuardada));
    }

    @PostMapping("/asociar/{materiaOrigenId}/{materiaDestinoId}")
    public ResponseEntity<Void> asociarMaterias(@PathVariable Long materiaOrigenId,
                                            @PathVariable Long materiaDestinoId){
        materiaService.asociarMateria(materiaOrigenId, materiaDestinoId);
        return ResponseEntity.ok().build();
    }

}
