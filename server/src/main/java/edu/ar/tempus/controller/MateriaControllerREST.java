package edu.ar.tempus.controller;

import edu.ar.tempus.controller.dto.MateriaDTOResponse;
import edu.ar.tempus.model.Materia;
import edu.ar.tempus.service.MateriaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/materia")
public final class MateriaControllerREST {


    private final MateriaService materiaService;

    public MateriaControllerREST(MateriaService materiaService) {
        this.materiaService = materiaService;
    }

    @GetMapping("/obtener/{materiaId}")
    public ResponseEntity<?>  obtenerMateria(@PathVariable("materiaId") Long materiaId){
        Materia materia = materiaService.recuperar(materiaId);

        MateriaDTOResponse response = MateriaDTOResponse.desdeModelo(materia);

        return ResponseEntity.ok(response);
    }

}
