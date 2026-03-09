package edu.ar.tempus.exceptions.business;

import java.util.List;

public class DependenciaCircularException extends BusinessException {

    public DependenciaCircularException(Long origenId, Long destinoId) {
        super("Agregar la relación %d → %d crearía una dependencia circular"
                .formatted(origenId, destinoId));
    }
    public DependenciaCircularException(Long materiaOrigenId, List<Long> circulares) {
        super("Agregar la relación %d → %s crearía una dependencia circular"
                .formatted(materiaOrigenId, circulares));
    }
}
