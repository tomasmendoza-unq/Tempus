package edu.ar.tempus.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Materia {


    private Long materiaId;

    private String materiaNombre;

    @Builder.Default
    private Set<Materia> correlativas= new HashSet<>();;

    public void agregarCorrelativas(Set<Materia> correlativas) {
        this.correlativas.addAll(correlativas);
    }


    public void agregarCorrelativa(Materia correlativa) {
        //TODO: VERIFICAR SI NECESITA ALGUNA VALIDACION
        this.correlativas.add(correlativa);
    }
}
