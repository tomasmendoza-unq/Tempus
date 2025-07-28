package com.tempus.Factory.impls;

import com.tempus.Factory.AbstractDTOFactory;
import com.tempus.dto.materia.MateriaResponseDTO;
import com.tempus.models.Materia;
import org.springframework.stereotype.Component;

@Component
public class MateriaFactory extends AbstractDTOFactory {

    public MateriaResponseDTO toResponseDTO(Materia materia){
        return toDTO(materia, MateriaResponseDTO.class);
    }

}
