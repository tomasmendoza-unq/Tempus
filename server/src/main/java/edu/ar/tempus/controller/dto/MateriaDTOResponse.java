package edu.ar.tempus.controller.dto;

import edu.ar.tempus.model.Materia;

<<<<<<< HEAD
public record MateriaDTOResponse(
        Long materiaId,
        String materiaNombre
) {
    public static MateriaDTOResponse desdeModelo(Materia materia) {
        return new MateriaDTOResponse(materia.getMateriaId(), materia.getMateriaNombre());
    }
=======
public record MateriaDTOResponse(long id, String nombre) {

    public static MateriaDTOResponse desdeModelo(Materia materia) {
        return new MateriaDTOResponse(materia.getMateriaId(), materia.getMateriaNombre());
    }

>>>>>>> d436da0357dceec1b1c24eb4af3bd783e8a2bfcb
}
