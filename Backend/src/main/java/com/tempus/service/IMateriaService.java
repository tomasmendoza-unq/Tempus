package com.tempus.service;

import com.tempus.dto.materia.MateriaPostDTO;
import com.tempus.dto.materia.MateriaResponseDTO;
import com.tempus.dto.materia.MateriaSimpleDTO;

import java.util.List;

public interface IMateriaService {
    MateriaResponseDTO getMateria(Long id);

    List<MateriaSimpleDTO> getMaterias();

    MateriaSimpleDTO createdMateria(MateriaPostDTO materiaPostDTO);

    MateriaSimpleDTO putMateria(MateriaPostDTO materiaPostDTO, Long id);

    void deleteMateria(Long id);
}
