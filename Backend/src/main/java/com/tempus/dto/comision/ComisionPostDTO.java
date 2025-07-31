package com.tempus.dto.comision;

import com.tempus.enums.Turno;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ComisionPostDTO {

    private Turno turno;

    private Long idMateria;
}
