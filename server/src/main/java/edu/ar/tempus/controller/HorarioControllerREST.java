package edu.ar.tempus.controller;

import edu.ar.tempus.controller.dto.horario.HorarioDTORequest;
import edu.ar.tempus.controller.dto.horario.HorarioDTOResponse;
import edu.ar.tempus.exceptions.business.BusinessException;
import edu.ar.tempus.model.Horario;
import edu.ar.tempus.service.HorarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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
}
