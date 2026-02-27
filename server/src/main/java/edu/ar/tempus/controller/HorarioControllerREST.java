package edu.ar.tempus.controller;

import edu.ar.tempus.controller.dto.horario.HorarioDTOResponse;
import edu.ar.tempus.exceptions.business.BusinessException;
import edu.ar.tempus.model.Horario;
import edu.ar.tempus.service.HorarioService;
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
    public ResponseEntity<HorarioDTOResponse> generarHorarioCompatible(@RequestBody List<Long> materiasIds) {
        Horario horarioCompatible = horarioService.generarUnHorarioCon(materiasIds)
                .orElseThrow(() -> new BusinessException("No se encontró una combinación de comisiones compatible para las materias solicitadas"));

        return ResponseEntity.ok(HorarioDTOResponse.desdeModelo(horarioCompatible));
    }
}
