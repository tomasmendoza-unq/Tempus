package edu.ar.tempus.service;

import edu.ar.tempus.model.Comision;

import java.util.List;

public interface ComisionService {
    Comision guardar(Comision comision, Long materiaId);

    Comision recuperar(Long comisionId);

    List<Comision> encontrarIdsUnaCombinacionCompatible(List<Long> materiasIds);
}
