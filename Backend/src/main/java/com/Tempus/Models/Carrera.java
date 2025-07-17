package com.Tempus.Models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;

@Entity
@Data
public class Carrera {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Long id_carrera;

    protected String nombre;

    @OneToMany(mappedBy = "carrera")
    protected Set<Materia> materias;
}
