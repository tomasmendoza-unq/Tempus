package com.Tempus.DTO;

import com.Tempus.Models.Materia;
import com.Tempus.Factory.IMateriaFactory;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Getter
@SuperBuilder
@NoArgsConstructor
public class MateriaCorrelativaDTO extends MateriaDTO{

    private List<MateriaDTO> correlativas;

    public MateriaCorrelativaDTO(Long id, String nombre, Long id_carrera, List<MateriaDTO> correlativas) {
        super(id, nombre, id_carrera);
        this.correlativas = correlativas;
    }

    @Override
    public Materia toEntity(IMateriaFactory materiaFactory) {
        return materiaFactory.createCorrelativa(this);
    }

}
