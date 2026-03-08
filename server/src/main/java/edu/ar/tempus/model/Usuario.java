package edu.ar.tempus.model;

import edu.ar.tempus.exceptions.business.YaSeEncuentraSuscritoALaCarrera;
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

    @Builder.Default
    private List<Carrera> carreras = new ArrayList<>();

    public void anotarseAComisiones(List<Comision> comisiones) {
        this.comisiones.addAll(comisiones);
    }

    public void aprobarMaterias(List<Materia> materiasAprobadas) {
        this.materiasAprobadas.addAll(materiasAprobadas);
    }

    public void desanotarseDeComisiones(List<Comision> comisiones) {
        this.comisiones.removeAll(comisiones);
    }

    public void desaprobarMateria(Materia materia) {
        materiasAprobadas.remove(materia);
    }

    public void suscribirseACarrera(Carrera carrera) {
        if(carreras.contains(carrera)) throw new YaSeEncuentraSuscritoALaCarrera("El alumno ya se encuentra suscripto a la carrera");
        carreras.add(carrera);
    }
}
