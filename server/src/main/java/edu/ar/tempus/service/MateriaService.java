package edu.ar.tempus.service;

import edu.ar.tempus.model.Materia;

public interface MateriaService {


    Materia guardar(Materia materia);

    Materia recuperar(Long materiaId);
}
