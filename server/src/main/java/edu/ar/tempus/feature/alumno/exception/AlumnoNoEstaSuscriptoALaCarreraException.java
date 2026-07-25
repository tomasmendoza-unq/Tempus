package edu.ar.tempus.feature.alumno.exception;

import edu.ar.tempus.exceptions.business.BusinessException;

public class AlumnoNoEstaSuscriptoALaCarreraException extends BusinessException {
    public AlumnoNoEstaSuscriptoALaCarreraException(String message) {
        super(message);
    }
}
