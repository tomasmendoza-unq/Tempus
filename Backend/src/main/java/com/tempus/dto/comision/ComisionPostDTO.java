package com.tempus.dto.comision;

import com.tempus.enums.Turno;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ComisionPostDTO {

    private LocalTime horario;

    private Long idMateria;
}
