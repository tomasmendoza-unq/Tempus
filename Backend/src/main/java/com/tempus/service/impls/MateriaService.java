package com.tempus.service.impls;

import com.tempus.Factory.models.IMateriaFactory;
import com.tempus.data.IMateriaFinder;
import com.tempus.dto.materia.MateriaPostDTO;
import com.tempus.dto.materia.MateriaResponseDTO;
import com.tempus.dto.materia.MateriaSimpleDTO;
import com.tempus.models.Materia;
import com.tempus.repository.IMateriaRepository;
import com.tempus.service.IMateriaService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MateriaService implements IMateriaService {

    @Autowired
    private IMateriaRepository materiaRepository;

    @Autowired
    private IMateriaFactory materiaFactory;

    @Autowired
    private IMateriaFinder finderMateria;

    @Override
    public MateriaResponseDTO getMateria(Long id) {
        return this.toResponseDTO(this.findMateriaWithCorrelativas(id));
    }

    private MateriaResponseDTO toResponseDTO(Materia materia) {
        return materiaFactory.toResponseDTO(materia);
    }

    private Materia findMateriaWithCorrelativas(Long id){
        return finderMateria.findMateriaWithCorrelativas(id);
    }

    private Materia findMateria(Long id) {
        return finderMateria.findById(id);
    }

    @Override
    public List<MateriaSimpleDTO> getMaterias() {
        return this.findAll()
                .stream()
                .map(this::toSimpleDTO)
                .toList();
    }

    private List<Materia> findAll() {
        return finderMateria.findAll();
    }

    private MateriaSimpleDTO toSimpleDTO(Materia materia) {
        return materiaFactory.toSimpleDTO(materia);
    }

    @Override
    public MateriaSimpleDTO createdMateria(MateriaPostDTO materiaPostDTO) {
        Materia materia = materiaFactory.toEntity(materiaPostDTO);
        Materia saved = this.save(materia);

        return this.toSimpleDTO(saved);
    }

    private Materia save(Materia materia) {
        return materiaRepository.save(materia);
    }

    @Transactional
    @Override
    public MateriaSimpleDTO putMateria(MateriaPostDTO materiaPostDTO, Long id) {
        Materia materia = this.findMateria(id);
        materiaFactory.updateEntity(materia, materiaPostDTO);
        Materia saved = this.save(materia);

        return this.toSimpleDTO(saved);
    }

    @Override
    public void deleteMateria(Long id) {
        materiaRepository.delete(this.findMateria(id));
    }
}
