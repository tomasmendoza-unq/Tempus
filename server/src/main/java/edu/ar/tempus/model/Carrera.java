package edu.ar.tempus.model;

import lombok.*;

import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "materias")
@EqualsAndHashCode(of = "id")
@Data
public class Carrera {

    private Long id;

    private String nombreCarrera;

    private List<Materia> materias;
}
