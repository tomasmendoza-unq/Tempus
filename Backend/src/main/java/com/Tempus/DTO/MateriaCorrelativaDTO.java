package com.Tempus.DTO;

import com.Tempus.Models.Materia;
import com.Tempus.Services.IMateriaFactory;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Getter
@SuperBuilder
public class MateriaCorrelativaDTO extends MateriaDTO{

    private List<MateriaDTO> correlativas;

    public MateriaCorrelativaDTO(Long id, String nombre, List<MateriaDTO> correlativas) {
        super(id, nombre);
        this.correlativas = correlativas;
    }

    @Override
    public Materia toEntity(IMateriaFactory materiaFactory) {
        return materiaFactory.createCorrelativa(this);
    }

}
