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
public class ClaseHorario {
    private Long id;
    private DiasSemana dia;
    private LocalTime inicio;
    private LocalTime fin;
}