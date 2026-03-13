package edu.ar.tempus.controller;

import edu.ar.tempus.controller.dto.claseHorario.ClaseHorarioDTOResponse;
import edu.ar.tempus.controller.dto.claseHorario.UpdateClaseHorarioDTORequest;
import edu.ar.tempus.model.ClaseHorario;
import edu.ar.tempus.service.ClaseHorarioService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/claseHorario")
public class ClaseHorarioControllerREST {

    private final ClaseHorarioService claseHorarioService;

    public ClaseHorarioControllerREST(ClaseHorarioService claseHorarioService) {
        this.claseHorarioService = claseHorarioService;
    }

    @PatchMapping("/actualizar")
    public ResponseEntity<List<ClaseHorarioDTOResponse>> actualizar(@Valid @RequestBody List<UpdateClaseHorarioDTORequest> request) {
        List<ClaseHorario> claseHorarios = claseHorarioService.actualizar(request);

        List<ClaseHorarioDTOResponse> horarioDTOResponses = claseHorarios.stream().map(ClaseHorarioDTOResponse::desdeModelo).toList();

        return ResponseEntity.ok(horarioDTOResponses);
    }
}
