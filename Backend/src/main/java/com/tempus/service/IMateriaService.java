package com.tempus.service;

import com.tempus.dto.materia.MateriaPostDTO;
import com.tempus.dto.materia.MateriaResponseDTO;

import java.util.List;

public interface IMateriaService {
    MateriaResponseDTO getMateria(Long id);

    List<MateriaResponseDTO> getMaterias();

    MateriaResponseDTO createdMateria(MateriaPostDTO materiaPostDTO);

    MateriaResponseDTO putMateria(MateriaPostDTO materiaPostDTO, Long id);

    void deleteMateria(Long id);
}
