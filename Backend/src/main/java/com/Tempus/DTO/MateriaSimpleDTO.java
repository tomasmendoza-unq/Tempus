package com.Tempus.DTO;

import com.Tempus.Models.Materia;
import com.Tempus.Services.IMateriaFactory;
import lombok.experimental.SuperBuilder;

@SuperBuilder
public class MateriaSimpleDTO extends MateriaDTO{
    public MateriaSimpleDTO(Long id, String nombre) {
        super(id, nombre);
    }

    @Override
    public Materia toEntity(IMateriaFactory materiaFactory) {
        return materiaFactory.createSimple(this);
    }
}
