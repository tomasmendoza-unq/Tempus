package com.Tempus.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@Builder
@AllArgsConstructor
public class Carrera {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Long id_carrera;

    protected String nombre;

    @OneToMany(mappedBy = "carrera", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @Builder.Default
    protected Set<Materia> materias = new HashSet<>();

    public Carrera() {
    }
}
