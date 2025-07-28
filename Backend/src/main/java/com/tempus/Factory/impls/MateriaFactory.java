package com.tempus.Factory.impls;

import com.tempus.Factory.AbstractDTOFactory;
import com.tempus.data.IEntityFinder;
import com.tempus.dto.materia.MateriaPostDTO;
import com.tempus.dto.materia.MateriaResponseDTO;
import com.tempus.models.Carrera;
import com.tempus.models.Materia;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MateriaFactory extends AbstractDTOFactory {

    @Autowired
    private IEntityFinder<Carrera> finderCarrera;

    public MateriaResponseDTO toResponseDTO(Materia materia){
        return toDTO(materia, MateriaResponseDTO.class);
    }

    public Materia toEntity(MateriaPostDTO materiaPostDTO){
        Materia materia = toEntity(materiaPostDTO, Materia.class);
        Carrera carrera = finderCarrera.findById(materiaPostDTO.getIdCarrera());
        materia.setCarrera(carrera);
        return materia;
    }
}
