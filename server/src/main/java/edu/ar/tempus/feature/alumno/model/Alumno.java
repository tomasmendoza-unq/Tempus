package edu.ar.tempus.feature.alumno.model;

import edu.ar.tempus.exceptions.business.SinCarreraActivaException;
import edu.ar.tempus.exceptions.business.UsuarioNoPerteneceALaCarreraException;
import edu.ar.tempus.feature.alumno.exception.AlumnoNoEstaSuscriptoALaCarreraException;
import edu.ar.tempus.feature.alumno.exception.YaSeEncuentraSuscritoALaCarrera;
import edu.ar.tempus.model.Carrera;
import edu.ar.tempus.model.Comision;
import edu.ar.tempus.model.Materia;
import edu.ar.tempus.model.Usuario;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@SuperBuilder
@Data
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
public class Alumno extends Usuario {

    @Builder.Default
    private List<Comision> comisiones = new ArrayList<>();

    @Builder.Default
    private List<Materia> materiasAprobadas = new ArrayList<>();

    @Builder.Default
    private List<Carrera> carreras = new ArrayList<>();

    private Carrera carreraActiva;

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

        seleccionarCarreraActiva(carrera);
    }

    public Carrera getCarreraActiva() {
        if (carreraActiva == null) {
            throw new SinCarreraActivaException("El alumno no está inscripto a ninguna carrera");
        }
        return carreraActiva;
    }

    public void seleccionarCarreraActiva(Carrera carrera){
        if(!carreras.contains(carrera))
            throw new UsuarioNoPerteneceALaCarreraException("El usuario no pertenece a esta carrera");

        this.carreraActiva = carrera;
    }

    public void desuscribirseACarrera(Carrera carrera) {
        if(!carreras.contains(carrera)) throw new AlumnoNoEstaSuscriptoALaCarreraException("El alumno no esta suscripto a la carrera");
        carreras.remove(carrera);
    }

    public void aprobarMateria(Materia materia) {
        materiasAprobadas.add(materia);
    }
}
