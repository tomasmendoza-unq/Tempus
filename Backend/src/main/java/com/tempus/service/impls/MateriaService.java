package com.tempus.service.impls;

import com.tempus.Factory.impls.MateriaFactory;
import com.tempus.data.IEntityFinder;
import com.tempus.dto.materia.MateriaPostDTO;
import com.tempus.dto.materia.MateriaResponseDTO;
import com.tempus.models.Materia;
import com.tempus.repository.IMateriaRepository;
import com.tempus.service.IMateriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MateriaService implements IMateriaService {

    @Autowired
    private IMateriaRepository materiaRepository;

    @Autowired
    private MateriaFactory materiaFactory;

    @Autowired
    private IEntityFinder<Materia> finderMateria;

    @Override
    public MateriaResponseDTO getMateria(Long id) {
        return this.toResponseDTO(this.findMateria(id));
    }

    private MateriaResponseDTO toResponseDTO(Materia materia) {
        return materiaFactory.toResponseDTO(materia);
    }

    private Materia findMateria(Long id) {
        return finderMateria.findById(id);
    }

    @Override
    public List<MateriaResponseDTO> getMaterias() {
        return materiaRepository.findAll()
                .stream()
                .map(this::toResponseDTO)
                .toList();
    }

    @Override
    public MateriaResponseDTO createdMateria(MateriaPostDTO materiaPostDTO) {
        Materia materia = materiaFactory.toEntity(materiaPostDTO);
        Materia saved = this.save(materia);

        return this.toResponseDTO(saved);
    }

    private Materia save(Materia materia) {
        return materiaRepository.save(materia);
    }

    @Override
    public MateriaResponseDTO putMateria(MateriaPostDTO materiaPostDTO, Long id) {
        Materia materia = this.findMateria(id);
        materiaFactory.updateEntity(materia, materiaPostDTO);
        Materia saved = this.save(materia);

        return this.toResponseDTO(saved);
    }

    @Override
    public void deleteMateria(Long id) {
        materiaRepository.delete(this.findMateria(id));
    }
}
