package edu.ar.tempus.model;

import lombok.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "materia")
@Data
public class Materia {


    private Long materiaId;

    private String materiaNombre;


    @Builder.Default
    private List<Comision> comisiones = new ArrayList<>();

    @Builder.Default
    private Set<Materia> correlativas= new HashSet<>();;

    public void agregarCorrelativas(Set<Materia> correlativas) {
        this.correlativas.addAll(correlativas);
    }


    public void agregarCorrelativa(Materia correlativa) {
        //TODO: VERIFICAR SI NECESITA ALGUNA VALIDACION
        this.correlativas.add(correlativa);
    }

    public void agregarComision(Comision comision) {
        this.comisiones.add(comision);
    }
}
