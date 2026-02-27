package edu.ar.tempus.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Usuario {

    private Long id;
    private String email;
    private String password;

    private String nombre;
    private String apellido;

    @Builder.Default
    private boolean enabled = true;

    private String telefono;

    private Role role;

}
