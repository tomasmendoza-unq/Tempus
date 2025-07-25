package com.Tempus.Services.impls;

import com.Tempus.DTO.ComisionCreatedDTO;
import com.Tempus.DTO.ComisionDTO;
import com.Tempus.DTO.MateriaDTO;
import com.Tempus.Factory.impls.ComisionFactory;
import com.Tempus.Models.Comision;
import com.Tempus.Models.Materia;
import com.Tempus.Repository.IComisionRepository;
import com.Tempus.Repository.IMateriaRepository;
import com.Tempus.Services.IComisionService;
import com.Tempus.Services.IMateriaService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ComisionService implements IComisionService {

    @Autowired
    private IComisionRepository comisionRepository;

    @Autowired
    private ComisionFactory comisionFactory;

    @Override
    public ComisionCreatedDTO createdComision(ComisionCreatedDTO comisionDTO) {
        Comision comision = this.save(comisionDTO);

        return comisionFactory.toCreatedDTO(comision);
    }

    @Override
    public List<ComisionDTO> getComisiones() {
        return List.of();
    }

    private Comision save(ComisionCreatedDTO comisionDTO) {
        return comisionRepository.save(comisionFactory.toEntity(comisionDTO));
    }

}
