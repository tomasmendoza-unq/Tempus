package com.tempus.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Materia {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idMateria;

    private String nombreMateria;

    @ManyToOne
    @JoinColumn(name = "idCarrera")
    private Carrera carrera;
}
