package edu.ar.tempus.utils;

import org.springframework.security.core.Authentication;

public interface AuthUtils {
    public Long getAlumnoId(Authentication authentication);
}
