package edu.ar.tempus.feature.alumno.exception;

import edu.ar.tempus.exceptions.business.BusinessException;

public class YaSeEncuentraSuscritoALaCarrera extends BusinessException {
    public YaSeEncuentraSuscritoALaCarrera(String message) {
        super(message);
    }
}
