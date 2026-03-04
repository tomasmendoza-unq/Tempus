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

    @Builder.Default
    private List<Materia> materiasAprobadas = new ArrayList<>();

    public void anotarseAComisiones(List<Comision> comisiones) {
        this.comisiones.addAll(comisiones);
    }

    public void aprobarMaterias(List<Materia> materiasAprobadas) {
        this.materiasAprobadas.addAll(materiasAprobadas);
    }

    public void desanotarseDeComisiones(List<Comision> comisiones) {
        System.out.println("Comisiones del alumno: " + this.comisiones.stream().map(Comision::getComisionId).toList());
        System.out.println("Comisiones a remover: " + comisiones.stream().map(Comision::getComisionId).toList());
        this.comisiones.removeAll(comisiones);
        System.out.println("Comisiones después: " + this.comisiones.stream().map(Comision::getComisionId).toList());
    }
}
