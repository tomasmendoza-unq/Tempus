package com.Tempus.DTO;

import com.Tempus.Enums.Turno;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ComisionCreatedDTO {

    private Long id;
    private String cuatrimestre;
    private Turno turno;
    private Long idMateria;
}
