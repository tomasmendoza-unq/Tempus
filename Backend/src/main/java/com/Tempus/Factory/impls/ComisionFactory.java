package com.Tempus.Factory.impls;

import com.Tempus.DTO.ComisionCreatedDTO;
import com.Tempus.DTO.ComisionDTO;
import com.Tempus.DTO.MateriaDTO;
import com.Tempus.Factory.AbstractDTOFactory;
import com.Tempus.Models.Comision;
import com.Tempus.Models.Materia;
import com.Tempus.Services.IMateriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ComisionFactory extends AbstractDTOFactory {

    @Autowired
    private IMateriaService materiaService;


    public ComisionDTO toDTO(Comision comision){
        MateriaDTO materiaDTO = materiaService.findByIdMateriaDTO(comision.getMateria().getId());
        ComisionDTO comisionDTO = toDTO(comision, ComisionDTO.class);
        comisionDTO.setMateriaDTO(materiaDTO);
        return comisionDTO;
    }

    public ComisionCreatedDTO toCreatedDTO(Comision comision){
        return toDTO(comision, ComisionCreatedDTO.class);
    }

    public Comision toEntity(ComisionCreatedDTO comisionCreatedDTO){
        Materia materia = findMateria(comisionCreatedDTO);
        Comision comision = toEntity(comisionCreatedDTO, Comision.class);
        comision.setMateria(materia);
        return comision;
    }

    private Materia findMateria(ComisionCreatedDTO comisionCreatedDTO) {
        return materiaService.findByIdMateria(comisionCreatedDTO.getIdMateria());
    }

    public void updateEntityFromDTO(ComisionCreatedDTO comisionCreatedDTO, Comision comision) {
        super.updateEntityFromDTO(comisionCreatedDTO, comision);
        comision.setMateria(findMateria(comisionCreatedDTO));
    }
}
