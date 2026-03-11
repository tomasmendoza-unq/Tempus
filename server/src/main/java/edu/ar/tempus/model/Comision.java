package edu.ar.tempus.model;

import lombok.*;

import java.time.LocalTime;
import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "materia")
@EqualsAndHashCode(of = "comisionId")
@Data
public class Comision {

    private Long comisionId;
    private Materia materia;
    private String comisionNombre;
    private List<ClaseHorario> clases;


}

