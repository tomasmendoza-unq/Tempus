package edu.ar.tempus.controller.dto.horario;

import edu.ar.tempus.controller.dto.comision.ComisionDTOResponseSimple;
import edu.ar.tempus.model.Horario;

import java.util.List;

public record HorarioDTOResponse(
        List<ComisionDTOResponseSimple> comisiones
) {
    public static HorarioDTOResponse desdeModelo(Horario horarioCompatible) {
        return new HorarioDTOResponse(
                horarioCompatible.getComisiones().stream().map(ComisionDTOResponseSimple::desdeModelo).toList()
        );

    }
}
