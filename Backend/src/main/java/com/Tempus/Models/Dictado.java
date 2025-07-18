package com.Tempus.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Builder;

@Entity
@Builder
public class Dictado {

    @Id
    private Long id_dictado;

    @ManyToOne
    @JoinColumn(name = "id_profesor")
    private Profesor profesores;

    @ManyToOne
    @JoinColumn(name = "id_comision")
    private Comision comisiones;

}
