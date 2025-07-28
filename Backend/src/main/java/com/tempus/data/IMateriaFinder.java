package com.tempus.data;

import com.tempus.models.Materia;

import java.util.List;

public interface IMateriaFinder extends IEntityFinder<Materia>{
    public List<Materia> findMateriasByCarreraId(Long idCarrera);
}
