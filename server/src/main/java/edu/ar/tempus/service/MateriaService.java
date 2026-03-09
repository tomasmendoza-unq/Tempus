package edu.ar.tempus.service;

import edu.ar.tempus.model.Materia;

import java.util.List;

public interface MateriaService {
    Materia guardar(Materia materia);

    Materia recuperar(Long materiaId);

    void asociarMateria(Long materiaId, Long materiaId1);

    void asociarMaterias(Long materiaId, List<Long> materiaIds);

    List<Materia> recuperarTodos();

    List<Materia> recuperarMateriasDisponibles(Long alumnoId);

    List<Materia> recuperarMateriasPorNombre(String nombreMateria);

}
