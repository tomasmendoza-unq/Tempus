package com.Tempus.DTO;

import lombok.Builder;
import lombok.Data;

import java.util.Set;

@Data
@Builder
public class CarreraDTO {

    private Set<MateriaDTO> materias;
    private String nombre;

}
