package com.Tempus.Services;

import com.Tempus.DTO.MateriaCorrelativaDTO;
import com.Tempus.DTO.MateriaDTO;
import org.springframework.stereotype.Service;

@Service
public interface IMateriaService {
    public MateriaDTO getCorrelativas(long id);

    public MateriaDTO createdMateria(MateriaDTO materiaDTO);
}
