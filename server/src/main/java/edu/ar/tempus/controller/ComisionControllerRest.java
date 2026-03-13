package edu.ar.tempus.controller;

import edu.ar.tempus.controller.dto.comision.ComisionDTORequest;
import edu.ar.tempus.controller.dto.comision.ComisionDTOResponse;
import edu.ar.tempus.controller.dto.comision.UpdateComisionDTORequest;
import edu.ar.tempus.model.Comision;
import edu.ar.tempus.service.ComisionService;
import edu.ar.tempus.utils.AuthUtils;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comision")
public final class ComisionControllerRest {

    private final ComisionService comisionService;
    private final AuthUtils authUtils;

    public ComisionControllerRest(ComisionService comisionService, AuthUtils authUtils) {
        this.comisionService = comisionService;
        this.authUtils = authUtils;
    }

    @GetMapping
    public ResponseEntity<Page<ComisionDTOResponse>> obtenerComisiones(
            @RequestParam(defaultValue = "0") int page,
            Authentication authentication
    ){
        Long alumnoId = authUtils.getAlumnoId(authentication);
        Page<Comision> comisiones = comisionService.recuperarComisiones(page, alumnoId);
        Page<ComisionDTOResponse> response = comisiones.map(ComisionDTOResponse::desdeModelo);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{comisionId}")
    public ResponseEntity<ComisionDTOResponse> obtenerComision(@PathVariable("comisionId") Long comisionId){
        Comision comision = comisionService.recuperar(comisionId);

        return ResponseEntity.status(HttpStatus.FOUND).body(ComisionDTOResponse.desdeModelo(comision));
    }

    @PostMapping("/crear")
    public ResponseEntity<ComisionDTOResponse> crearComision(@Valid @RequestBody ComisionDTORequest request){
        Comision comision = ComisionDTORequest.aModelo(request);

        Comision comisionGuardada = comisionService.guardar(comision, request.materiaId());

        return ResponseEntity.status(HttpStatus.CREATED).body(ComisionDTOResponse.desdeModelo(comisionGuardada));
    }

    @PutMapping("/{idComision}")
    public ResponseEntity<ComisionDTOResponse> actualizarComision(
            @Valid @RequestBody UpdateComisionDTORequest request,
            @PathVariable("idComision") Long idComision){

        Comision comision = comisionService.actualizar(idComision, request);

        ComisionDTOResponse response = ComisionDTOResponse.desdeModelo(comision);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

}
