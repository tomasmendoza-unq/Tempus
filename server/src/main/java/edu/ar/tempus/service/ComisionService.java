package edu.ar.tempus.service;

import edu.ar.tempus.model.Comision;

import java.util.List;

public interface ComisionService {
    Comision guardar(Comision comision, Long materiaId);

    Comision recuperar(Long comisionId);

    List<List<Comision>>  encontrarIdsNCombinacionCompatible(List<Long> materiasIds, Integer cantidadHorarios);

    List<Comision> recuperarPorIds(List<Long> comisionIds);

    void validarSuperPosicion(List<Long> comisionIds, List<Long> comisionesAnotadas);

    boolean hayComisionesDeMismaMateriaEnNuevas(List<Long> comisionIds);
}
