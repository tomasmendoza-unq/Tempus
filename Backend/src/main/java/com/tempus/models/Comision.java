package com.tempus.models;

import com.tempus.enums.Turno;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Comision {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long idComision;

    private Turno turno;

    private LocalTime horario;

    @ManyToOne
    @JoinColumn(name = "materia_id")
    private Materia materia;

}
