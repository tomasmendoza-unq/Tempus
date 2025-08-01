package com.tempus.Factory.impls;

import com.tempus.Factory.AbstractDTOFactory;
import com.tempus.Factory.IComisionFactory;
import com.tempus.Factory.IMateriaFactory;
import com.tempus.data.IEntityFinder;
import com.tempus.dto.comision.ComisionPostDTO;
import com.tempus.dto.comision.ComisionResponseDTO;
import com.tempus.dto.materia.MateriaSimpleDTO;
import com.tempus.models.Comision;
import com.tempus.models.Materia;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ComisionFactory extends AbstractDTOFactory implements IComisionFactory {
    @Autowired
    IEntityFinder<Materia> finderMateria;

    @Autowired
    IMateriaFactory materiaFactory;

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

    @Override
    public ComisionResponseDTO toResponseDTO(Comision comision) {
        ComisionResponseDTO comisionResponseDTO = toDTO(comision, ComisionResponseDTO.class);
        Materia materia = comision.getMateria();
        MateriaSimpleDTO materiaSimpleDTO = materiaFactory.toSimpleDTO(materia);

        comisionResponseDTO.setMateria(materiaSimpleDTO);

        return comisionResponseDTO;
    }

    @Override
    public void updateEntity(Comision comision, ComisionPostDTO comisionPostDTO) {
        Materia materia = findMateria(comisionPostDTO.getIdMateria());

        comision.setTurno(comisionPostDTO.getTurno());
        comision.setMateria(materia);
    }


}
