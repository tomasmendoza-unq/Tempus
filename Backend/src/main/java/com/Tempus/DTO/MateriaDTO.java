package com.Tempus.DTO;

import com.Tempus.Models.Materia;
import com.Tempus.Services.IMateriaFactory;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
public abstract class MateriaDTO {
    protected Long id;
    protected String nombre;

    public MateriaDTO(Long id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public abstract Materia toEntity(IMateriaFactory materiaFactory);
}
