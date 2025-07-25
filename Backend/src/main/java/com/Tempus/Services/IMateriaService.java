package com.Tempus.Services;

import com.Tempus.DTO.MateriaDTO;
import com.Tempus.Models.Materia;
import org.springframework.stereotype.Service;

@Service
public interface IMateriaService {
    public MateriaDTO findCorrelativasById(Long id);

    public MateriaDTO createdMateria(MateriaDTO materiaDTO);

    public Materia findByIdMateria(Long id);
}
