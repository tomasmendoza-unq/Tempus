package com.tempus.dto.materia;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MateriaResponseDTO {

    private String nombreMateria;

    private Long idCarrera;

    //private List<MateriaResponseDTO> correlativas;
}
