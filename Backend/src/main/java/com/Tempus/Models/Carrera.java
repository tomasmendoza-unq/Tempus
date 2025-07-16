package com.Tempus.Models;

import jakarta.persistence.*;

import java.util.Set;

@Entity
public class Carrera {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Long id_carrera;

    @OneToMany(mappedBy = "carrera")
    protected Set<Materia> materias;
}
