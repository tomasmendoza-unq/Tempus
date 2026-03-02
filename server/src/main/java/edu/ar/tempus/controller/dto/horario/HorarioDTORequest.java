package edu.ar.tempus.controller.dto.horario;

import java.util.List;

public record HorarioDTORequest (
        List<Long> materiasIds, Integer cantidadHorarios
){
}
