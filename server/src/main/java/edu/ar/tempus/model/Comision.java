package edu.ar.tempus.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;
import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Comision {

    private Long ComisionId;
    private Materia materia;
    private List<ClaseHorario> clases;


}

