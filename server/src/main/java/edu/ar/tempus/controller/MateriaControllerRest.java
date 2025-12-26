package edu.ar.tempus.controller;

import edu.ar.tempus.controller.dto.MateriaDTORequest;
import edu.ar.tempus.controller.dto.MateriaDTOResponse;
import edu.ar.tempus.model.Materia;
import edu.ar.tempus.service.MateriaService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/materia")
public final class MateriaControllerRest {

    private final MateriaService materiaService;

    public MateriaControllerRest(MateriaService materiaService) {
        this.materiaService = materiaService;
    }

    @GetMapping("/{id}")
    public MateriaDTOResponse getMateria(@PathVariable Long id) {
        Materia materia = materiaService.recuperar(id);

        return MateriaDTOResponse.desdeModelo(materia);
    }

    @PostMapping("/crear")
    public MateriaDTOResponse crearMateria(@RequestBody MateriaDTORequest materiaDTO) {
        Materia materia = MateriaDTORequest.aModelo(materiaDTO);

        Materia materiaGuardada = materiaService.guardar(materia);

        return MateriaDTOResponse.desdeModelo(materiaGuardada);
    }
}
