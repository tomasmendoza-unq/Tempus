package com.Tempus.Services.impls;

import com.Tempus.DTO.MateriaDTO;
import com.Tempus.Exceptions.ResourceNotFound;
import com.Tempus.Factory.impls.MateriaFactory;
import com.Tempus.Models.Materia;
import com.Tempus.Repository.IMateriaRepository;
import com.Tempus.Services.ICarreraService;
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

    public MateriaService(IMateriaRepository materiaRepository, MateriaFactory materiaFactory) {
        this.materiaRepository = materiaRepository;
        this.materiaFactory = materiaFactory;
    }

    @Transactional
    public MateriaDTO findCorrelativasById(long id){
        return this.findByIdMateria(id).toDTO();
    }

    @Override
    public MateriaDTO createdMateria(MateriaDTO materiaDTO) {
        Materia entidad = materiaFactory.factoryMethod(materiaDTO);
        Materia entidadGuardada = this.save(entidad);
        return entidadGuardada.toDTO();
    }

    private Materia save(Materia materia) {
        return materiaRepository.save(materia);
    }


    private Materia findByIdMateria(long id) {
        return materiaRepository
                .findById(id)
                .orElseThrow(
                    () -> new ResourceNotFound("No se encontro la materia")
                );
    }

}
