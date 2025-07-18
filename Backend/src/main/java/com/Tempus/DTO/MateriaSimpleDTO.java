package com.Tempus.DTO;

import com.Tempus.Models.Materia;
import com.Tempus.Factory.IMateriaFactory;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@NoArgsConstructor
public class MateriaSimpleDTO extends MateriaDTO{
    public MateriaSimpleDTO(Long id, String nombre, Long id_carrera) {
        super(id, nombre, id_carrera);
    }

    @Override
    public Materia toEntity(IMateriaFactory materiaFactory) {
        return materiaFactory.createSimple(this);
    }
}
