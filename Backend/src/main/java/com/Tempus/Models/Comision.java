package com.Tempus.Models;

import com.Tempus.Enums.Turno;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@Data
@Builder
public class Comision {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id_comision;

    @ManyToOne
    private Materia materia;

    @OneToMany(mappedBy = "comisiones")
    private List<Dictado> dictados;


    private String cuatrimestre;

    private Turno turno;

    public Comision() {
        this.dictados = new ArrayList<>();
    }

    public void addDictado(Dictado dictado){
        dictados.add(dictado);
    }
}
