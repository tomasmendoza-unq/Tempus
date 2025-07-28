package com.tempus.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Carrera {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idCarrera;

    private String nombreCarrera;

    @OneToMany(mappedBy = "carrera")
    private List<Materia> materias;

}
