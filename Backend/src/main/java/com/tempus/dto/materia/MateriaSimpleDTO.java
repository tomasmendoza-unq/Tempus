package com.tempus.dto.materia;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class MateriaSimpleDTO {

    private String nombreMateria;
    private Long idCarrera;
}
