package com.Tempus.Models;

import com.Tempus.DTO.MateriaCorrelativaDTO;
import com.Tempus.DTO.MateriaDTO;
import com.Tempus.DTO.MateriaSimpleDTO;
import com.Tempus.Enums.TipoDeMateria;
import jakarta.persistence.Entity;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Entity
@Builder
@NoArgsConstructor
public class MateriaSimple extends Materia{


    @Override
    public MateriaDTO toDTO() {
        return  MateriaSimpleDTO.builder()
                .id(id)
                .nombre(nombre)
                .id_carrera(getCarrera().getId_carrera())
                .build();
    }

}
