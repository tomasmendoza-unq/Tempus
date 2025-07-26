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
        Materia materia = materiaService.findByIdMateria(comisionCreatedDTO.getIdMateria());
        Comision comision = toEntity(comisionCreatedDTO, Comision.class);
        comision.setMateria(materia);
        return comision;
    }

}
