package edu.ar.tempus.model;

import lombok.*;

import java.time.LocalTime;
import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "materia")
@Data
public class Comision {

    private Long ComisionId;
    private Materia materia;
    private List<ClaseHorario> clases;


}

