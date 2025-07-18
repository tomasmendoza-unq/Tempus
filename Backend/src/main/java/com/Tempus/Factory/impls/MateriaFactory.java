package com.Tempus.Factory.impls;

import com.Tempus.DTO.MateriaCorrelativaDTO;
import com.Tempus.DTO.MateriaDTO;
import com.Tempus.DTO.MateriaSimpleDTO;
import com.Tempus.Exceptions.ResourceNotFound;
import com.Tempus.Factory.IMateriaFactory;
import com.Tempus.Models.Carrera;
import com.Tempus.Models.Materia;
import com.Tempus.Models.MateriaCorrelativa;
import com.Tempus.Models.MateriaSimple;
import com.Tempus.Repository.ICarreraRepository;
import com.Tempus.Repository.IMateriaRepository;
import com.Tempus.Services.ICarreraService;
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

    @Autowired
    private ICarreraRepository carreraRepository;

    public MateriaFactory(ModelMapper modelMapper, IMateriaRepository materiaRepository, ICarreraRepository carreraRepository) {
        this.modelMapper = modelMapper;
        this.materiaRepository = materiaRepository;
        this.carreraRepository = carreraRepository;
    }

    public Materia factoryMethod(MateriaDTO materiaDTO){

        return materiaDTO.toEntity(this);

    }

    @Override
    public Materia createSimple(MateriaSimpleDTO materiaSimpleDTO) {
        Materia materia = modelMapper.map(materiaSimpleDTO, MateriaSimple.class);

        materia.setCarrera(this.findById(materiaSimpleDTO.getId_carrera()));
        return materia;
    }

    @Override
    public Materia createCorrelativa(MateriaCorrelativaDTO materiaCorrelativaDTO) {
        MateriaCorrelativa  materia = modelMapper.map(materiaCorrelativaDTO, MateriaCorrelativa.class);
        List<Materia> materias = materiaCorrelativaDTO.getCorrelativas()
                .stream()
                .map(MateriaDTO::getId)
                .map(id ->  materiaRepository.findById(id).orElseThrow(
                        () -> new ResourceNotFound("No se encontro la materia")
                        )
                )
                .toList();
        materia.addCorrelativas(materias);
        return materia;
    }

    private Carrera findById(Long idCarrera) {
        return carreraRepository
                .findById(idCarrera)
                .orElseThrow(
                        () -> new ResourceNotFound("No se encontro la carrera")
                );
    }
}
