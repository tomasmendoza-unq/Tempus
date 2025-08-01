package com.tempus.Factory.models;

import com.tempus.dto.materia.MateriaPostDTO;
import com.tempus.dto.materia.MateriaResponseDTO;
import com.tempus.dto.materia.MateriaSimpleDTO;
import com.tempus.models.Materia;

public interface IMateriaFactory {
    MateriaResponseDTO toResponseDTO(Materia materia);

    MateriaSimpleDTO toSimpleDTO(Materia materia);

    Materia toEntity(MateriaPostDTO materiaPostDTO);

    void updateEntity(Materia materia, MateriaPostDTO materiaPostDTO);
}
