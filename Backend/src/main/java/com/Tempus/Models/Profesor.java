package com.Tempus.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Profesor {

    @Id
    private long id_profesor;

    private String nombre_profesor;

    @OneToMany(mappedBy = "profesores")
    private List<Dictado> dictado;

    public Profesor() {
        this.dictado = new ArrayList<>();
    }
}
