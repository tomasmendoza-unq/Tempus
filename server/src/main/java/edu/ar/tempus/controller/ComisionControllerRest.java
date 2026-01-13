package edu.ar.tempus.controller;

import edu.ar.tempus.controller.dto.comision.ComisionDTORequest;
import edu.ar.tempus.controller.dto.comision.ComisionDTOResponse;
import edu.ar.tempus.model.Comision;
import edu.ar.tempus.service.ComisionService;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/comision")
public final class ComisionControllerRest {

    private final ComisionService comisionService;

    public ComisionControllerRest(ComisionService comisionService) {
        this.comisionService = comisionService;
    }

    @GetMapping("/{comisionId}")
    public ResponseEntity<ComisionDTOResponse> obtenerComision(@PathVariable("comisionId") Long comisionId){
        Comision comision = comisionService.recuperar(comisionId);

        return ResponseEntity.status(HttpStatus.FOUND).body(ComisionDTOResponse.desdeModelo(comision));
    }

    @PostMapping("/crear")
    public ResponseEntity<ComisionDTOResponse> crearComision(@RequestBody ComisionDTORequest request){
        Comision comision = ComisionDTORequest.aModelo(request);

        Comision comisionGuardada = comisionService.guardar(comision, request.materiaId());

        return ResponseEntity.status(HttpStatus.CREATED).body(ComisionDTOResponse.desdeModelo(comisionGuardada));
    }


}
