package edu.ar.tempus.controller;

import edu.ar.tempus.controller.dto.claseHorario.ClaseHorarioDTOResponse;
import edu.ar.tempus.controller.dto.horario.HorarioDTORequest;
import edu.ar.tempus.controller.dto.horario.HorarioDTOResponse;
import edu.ar.tempus.controller.dto.claseHorario.UpdateClaseHorarioDTORequest;
import edu.ar.tempus.model.ClaseHorario;
import edu.ar.tempus.model.Horario;
import edu.ar.tempus.service.HorarioService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/horario")
public final class HorarioControllerREST {

    private final HorarioService horarioService;

    public HorarioControllerREST(HorarioService horarioService) {
        this.horarioService = horarioService;
    }

    @PostMapping("/compatible")
    public ResponseEntity<List<HorarioDTOResponse>> generarHorarioCompatible(@RequestBody HorarioDTORequest request) {
        List<Horario> horarioCompatible = horarioService.generarNHorarioCon(request.materiasIds(), request.cantidadHorarios());

        List<HorarioDTOResponse> response = horarioCompatible.stream().map(HorarioDTOResponse::desdeModelo).toList();

        return ResponseEntity.ok(response);
    }

    @PatchMapping("/actualizar")
    public ResponseEntity<List<ClaseHorarioDTOResponse>> actualizar(@Valid @RequestBody List<UpdateClaseHorarioDTORequest> request) {
        List<ClaseHorario> claseHorarios = horarioService.actualizar(request);

        List<ClaseHorarioDTOResponse> horarioDTOResponses = claseHorarios.stream().map(ClaseHorarioDTOResponse::desdeModelo).toList();

        return ResponseEntity.ok(horarioDTOResponses);
    }
}
