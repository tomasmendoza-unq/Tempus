package edu.ar.tempus.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

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

    @Builder.Default
    private List<Comision> comisiones = new ArrayList<>();

    public void anotarseAComisiones(List<Comision> comisiones) {
        this.comisiones.addAll(comisiones);
    }
}
