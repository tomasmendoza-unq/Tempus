package edu.ar.tempus.model;

import jakarta.validation.constraints.Null;
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

    @Builder.Default
    private String modalidad=null;


}

