package edu.ar.tempus.controller.dto.materia;

import edu.ar.tempus.controller.dto.comision.ComisionDTORequestSimple;
import edu.ar.tempus.model.Comision;
import edu.ar.tempus.model.Materia;


import java.util.List;

public record MateriaComisionDTORequest(
        String materiaNombre,
        List<ComisionDTORequestSimple> comisiones) {
    public Materia aModelo() {

        return Materia.builder()
                .materiaNombre(materiaNombre)
                .comisiones(comisiones.stream().map(ComisionDTORequestSimple::aModelo).toList())
                .build();
    }
}
