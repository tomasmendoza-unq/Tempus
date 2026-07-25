package edu.ar.tempus.model;

import edu.ar.tempus.exceptions.business.SinCarreraActivaException;
import edu.ar.tempus.exceptions.business.UsuarioNoPerteneceALaCarreraException;
import edu.ar.tempus.feature.alumno.exception.YaSeEncuentraSuscritoALaCarrera;
import edu.ar.tempus.feature.alumno.exception.AlumnoNoEstaSuscriptoALaCarreraException;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@SuperBuilder
@EqualsAndHashCode(of = "id")
public class Usuario {

    private Long id;

    private String email;

    private String password;

    private String nombre;

    private String apellido;

    @Builder.Default
    private boolean enabled = true;

    private Role role;
}
