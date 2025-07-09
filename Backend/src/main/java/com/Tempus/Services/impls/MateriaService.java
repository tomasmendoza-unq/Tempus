package com.Tempus.Services.impls;

import com.Tempus.DTO.MateriaCorrelativaDTO;
import com.Tempus.DTO.MateriaDTO;
import com.Tempus.Exceptions.MateriaNotFound;
import com.Tempus.Models.Materia;
import com.Tempus.Repository.IMateriaRepository;
import com.Tempus.Services.IMateriaService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MateriaService implements IMateriaService {

    @Autowired
    IMateriaRepository materiaRepository;
    @Autowired
    MateriaFactory materiaFactory;

    @Transactional
    public MateriaDTO getCorrelativas(long id){
        return this.findByIdMateria(id).toDTO();
    }

    @Override
    public MateriaDTO createdMateria(MateriaDTO materiaDTO) {
        Materia entidad = materiaFactory.factoryMethod(materiaDTO);
        Materia entidadGuardada = materiaRepository.save(entidad);
        return entidadGuardada.toDTO();
    }

    private void save(Materia materia) {
        materiaRepository.save(materia);
    }


    private Materia findByIdMateria(long id) {
        return materiaRepository.findById(id).orElseThrow(
                () -> new MateriaNotFound("No se encontro la materia")
        );
    }

}
