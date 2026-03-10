package edu.ar.tempus.exceptions.business;

import java.util.List;

public class RelacionCorrelativaYaExisteException extends BusinessException{
    public RelacionCorrelativaYaExisteException(Long materiaId, Long correlativaId) {
        super("La materia %d ya tiene a %d como correlativa".formatted(materiaId, correlativaId));
    }

    public RelacionCorrelativaYaExisteException(Long materiaId, List<Long> correlativaIds) {
        super("La materia %d ya tiene relación con las correlativas: %s"
                .formatted(materiaId, correlativaIds));
    }
}
