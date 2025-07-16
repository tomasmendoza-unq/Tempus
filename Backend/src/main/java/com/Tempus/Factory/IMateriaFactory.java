package com.Tempus.Factory;

import com.Tempus.DTO.MateriaCorrelativaDTO;
import com.Tempus.DTO.MateriaDTO;
import com.Tempus.DTO.MateriaSimpleDTO;
import com.Tempus.Models.Materia;

public interface IMateriaFactory {
    public Materia factoryMethod(MateriaDTO materiaDTO);

    Materia createSimple(MateriaSimpleDTO materiaSimpleDTO);

    Materia createCorrelativa(MateriaCorrelativaDTO materiaCorrelativaDTO);
}
