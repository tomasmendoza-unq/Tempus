package com.tempus.models;

import com.tempus.enums.DayOfWeek;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;
import java.util.Set;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Comision {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long idComision;

    private LocalTime horario;

    @ElementCollection(fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    private Set<DayOfWeek> diasDeCursada;

    @ManyToOne
    @JoinColumn(name = "materia_id")
    private Materia materia;

}
