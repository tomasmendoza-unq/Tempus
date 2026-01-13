package edu.ar.tempus.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Comision {

    private Long ComisionId;
    private LocalTime horarioInicio;
    private LocalTime horarioFin;
    private Materia materia;
}
