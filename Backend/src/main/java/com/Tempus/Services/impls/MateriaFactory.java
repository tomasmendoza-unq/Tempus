package com.Tempus.Services.impls;

import com.Tempus.DTO.MateriaCorrelativaDTO;
import com.Tempus.DTO.MateriaDTO;
import com.Tempus.DTO.MateriaSimpleDTO;
import com.Tempus.Exceptions.MateriaNotFound;
import com.Tempus.Models.Materia;
import com.Tempus.Models.MateriaCorrelativa;
import com.Tempus.Models.MateriaSimple;
import com.Tempus.Repository.IMateriaRepository;
import com.Tempus.Services.IMateriaFactory;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class MateriaFactory implements IMateriaFactory {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private IMateriaRepository materiaRepository;

    public Materia factoryMethod(MateriaDTO materiaDTO){

        return materiaDTO.toEntity(this);

    }

    @Override
    public Materia createSimple(MateriaSimpleDTO materiaSimpleDTO) {
        return modelMapper.map(materiaSimpleDTO, MateriaSimple.class) ;
    }

    @Override
    public Materia createCorrelativa(MateriaCorrelativaDTO materiaCorrelativaDTO) {
        MateriaCorrelativa  materia = modelMapper.map(materiaCorrelativaDTO, MateriaCorrelativa.class);
        List<Materia> materias = materiaCorrelativaDTO.getCorrelativas()
                .stream()
                .map(MateriaDTO::getId)
                .map(id ->  materiaRepository.findById(id).orElseThrow(
                        () -> new MateriaNotFound("No se encontro la materia")
                        )
                ).toList();
        materia.addCorrelativas(materias);
        return materia;
    }
}
