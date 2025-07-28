package com.tempus.Factory.impls;

import com.tempus.Factory.AbstractDTOFactory;
import com.tempus.data.IEntityFinder;
import com.tempus.dto.materia.MateriaPostDTO;
import com.tempus.dto.materia.MateriaResponseDTO;
import com.tempus.models.Carrera;
import com.tempus.models.Materia;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MateriaFactory extends AbstractDTOFactory {

    @Autowired
    private IEntityFinder<Carrera> finderCarrera;

    @Autowired
    private IEntityFinder<Materia> finderMateria;

    public MateriaResponseDTO toResponseDTO(Materia materia){
        MateriaResponseDTO materiaResponseDTO = toDTO(materia, MateriaResponseDTO.class);

        /*
        List<MateriaResponseDTO> correlativasDTOs = materia.getCorrelativas()
                .stream()
                .map(this::toResponseDTO)
                .toList();

        materiaResponseDTO.setCorrelativas(correlativasDTOs);
*/
        return materiaResponseDTO;
    }

    public Materia toEntity(MateriaPostDTO materiaPostDTO){
        Materia materia = toEntity(materiaPostDTO, Materia.class);
        Carrera carrera = finderCarrera.findById(materiaPostDTO.getIdCarrera());
        materia.setCarrera(carrera);
        setMaterias(materiaPostDTO, materia);

        return materia;
    }

    private void setMaterias(MateriaPostDTO materiaPostDTO, Materia materia) {
        materiaPostDTO.getCorrelativas()
                .stream()
                .map(this::findMateria)
                .forEach(materia::addCorrelativa);
    }

    private Materia findMateria(Long id) {
        return finderMateria.findById(id);
    }
}
