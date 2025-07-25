package com.Tempus.Services.impls;

import com.Tempus.DTO.ComisionCreatedDTO;
import com.Tempus.DTO.ComisionDTO;
import com.Tempus.DTO.MateriaDTO;
import com.Tempus.Models.Comision;
import com.Tempus.Models.Materia;
import com.Tempus.Repository.IComisionRepository;
import com.Tempus.Repository.IMateriaRepository;
import com.Tempus.Services.IComisionService;
import com.Tempus.Services.IMateriaService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ComisionService implements IComisionService {

    @Autowired
    private IComisionRepository comisionRepository;

    @Autowired
    private IMateriaService materiaService;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public ComisionCreatedDTO createdComision(ComisionCreatedDTO comisionDTO) {
        Comision comision = this.save(comisionDTO);
        ComisionCreatedDTO response = modelMapper.map(comision, ComisionCreatedDTO.class);

        return response;
    }

    private Comision save(ComisionCreatedDTO comisionDTO) {
        return comisionRepository.save(toEntity(comisionDTO));
    }

    private Comision toEntity(ComisionCreatedDTO comisionDTO){
        Materia materia = this.obtenerMateriaParaComision(comisionDTO.getIdMateria());
        Comision comision = modelMapper.map(comisionDTO, Comision.class);
        comision.setMateria(materia);
        return comision;
    }

    public Materia obtenerMateriaParaComision(Long id) {
        return materiaService.findByIdMateria(id);
    }
}
