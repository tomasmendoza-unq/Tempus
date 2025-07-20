package com.Tempus.DTO;

import com.Tempus.Models.Materia;
import lombok.Builder;
import lombok.Data;

import java.util.Set;

@Data
@Builder
public class CarreraDTO {

    private Set<MateriaResumenDTO> materias;
    private String nombre;


}
