package com.tempus.Factory.impls;

import com.tempus.Factory.AbstractDTOFactory;
import com.tempus.Factory.IComisionFactory;
import com.tempus.data.IEntityFinder;
import com.tempus.dto.comision.ComisionPostDTO;
import com.tempus.models.Comision;
import com.tempus.models.Materia;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ComisionFactory extends AbstractDTOFactory implements IComisionFactory {
    @Autowired
    IEntityFinder<Materia> finderMateria;

    @Override
    public Comision toEntity(ComisionPostDTO comisionPostDTO) {
        Comision comision = toEntity(comisionPostDTO, Comision.class);
        Materia materia = findMateria(comisionPostDTO.getIdMateria());
        comision.setMateria(materia);


        return comision;
    }

    private Materia findMateria(Long idMateria) {
        return finderMateria.findById(idMateria);
    }

    @Override
    public ComisionPostDTO toPostDTO(Comision comision) {
        return toDTO(comision, ComisionPostDTO.class);
    }


}
