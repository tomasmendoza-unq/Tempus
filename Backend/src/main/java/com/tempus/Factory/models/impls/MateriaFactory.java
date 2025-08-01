package com.tempus.Factory.models.impls;

import com.tempus.Factory.models.AbstractDTOFactory;
import com.tempus.Factory.models.IMateriaFactory;
import com.tempus.data.IEntityFinder;
import com.tempus.dto.materia.MateriaPostDTO;
import com.tempus.dto.materia.MateriaResponseDTO;
import com.tempus.dto.materia.MateriaSimpleDTO;
import com.tempus.models.Carrera;
import com.tempus.models.Materia;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MateriaFactory extends AbstractDTOFactory implements IMateriaFactory {

    @Autowired
    private IEntityFinder<Carrera> finderCarrera;

    @Autowired
    private IEntityFinder<Materia> finderMateria;

    public MateriaSimpleDTO toSimpleDTO(Materia materia){
        return toDTO(materia, MateriaSimpleDTO.class);
    }

    public MateriaResponseDTO toResponseDTO(Materia materia){
        MateriaResponseDTO materiaResponseDTO = toDTO(materia, MateriaResponseDTO.class);


        List<MateriaSimpleDTO> correlativasDTOs = materia.getCorrelativas()
                .stream()
                .map(this::toSimpleDTO)
                .toList();

        materiaResponseDTO.setCorrelativas(correlativasDTOs);

        return materiaResponseDTO;
    }

    public Materia toEntity(MateriaPostDTO materiaPostDTO){
        Materia materia = toEntity(materiaPostDTO, Materia.class);
        Carrera carrera = this.findCarrera(materiaPostDTO);
        materia.setCarrera(carrera);
        setMaterias(materiaPostDTO, materia);

        return materia;
    }


    public void updateEntity(Materia materia, MateriaPostDTO materiaPostDTO){
        Carrera carrera = this.findCarrera(materiaPostDTO);
        materia.setCarrera(carrera);
        materia.setNombreMateria(materiaPostDTO.getNombreMateria());

        materia.getCorrelativas().clear();
        setMaterias(materiaPostDTO, materia);
    }

    private void setMaterias(MateriaPostDTO materiaPostDTO, Materia materia) {
        materiaPostDTO.getCorrelativas()
                .stream()
                .map(this::findMateria)
                .forEach(materia::addCorrelativa);
    }

    private Carrera findCarrera(MateriaPostDTO materiaPostDTO){
        return finderCarrera.findById(materiaPostDTO.getIdCarrera());
    }

    private Materia findMateria(Long id) {
        return finderMateria.findById(id);
    }
}
