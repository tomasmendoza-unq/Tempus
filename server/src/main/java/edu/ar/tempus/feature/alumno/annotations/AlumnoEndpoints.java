package edu.ar.tempus.feature.alumno.annotations;


import edu.ar.tempus.shared.annotations.unAuthorized.UnAuthorized;
import org.springframework.security.access.prepost.PreAuthorize;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@UnAuthorized
@PreAuthorize("hasRole('ALUMNO')")
public @interface AlumnoEndpoints {
}
