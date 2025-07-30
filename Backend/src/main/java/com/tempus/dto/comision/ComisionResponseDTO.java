package com.tempus.dto.comision;

import com.tempus.dto.materia.MateriaSimpleDTO;
import com.tempus.enums.Turno;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ComisionResponseDTO {

    private Turno turno;

    private MateriaSimpleDTO materia;
}
