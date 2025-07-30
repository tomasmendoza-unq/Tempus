package com.tempus.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Materia {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long idMateria;

    private String nombreMateria;

    @ManyToOne
    @JoinColumn(name = "idCarrera")
    private Carrera carrera;

    @ManyToMany
    @JoinTable(
            name = "materia_correlativa",
            joinColumns = @JoinColumn(name = "materia_id"),
            inverseJoinColumns = @JoinColumn(name = "correlativa_id")
    )
    private List<Materia> correlativas = new ArrayList<>();

    public void addCorrelativa(Materia materia){
        correlativas.add(materia);
    }

}
