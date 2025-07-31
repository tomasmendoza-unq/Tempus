package com.tempus.data;

import com.tempus.models.Comision;

import java.util.List;

public interface IComisionFinder extends IEntityFinder<Comision>{

    Comision findComisionWithMateria(Long id);
}
