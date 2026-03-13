package edu.ar.tempus.service;

import edu.ar.tempus.model.Comision;
import edu.ar.tempus.model.Materia;
import org.springframework.data.domain.Page;
import java.util.List;

public interface ComisionService {
    Comision guardar(Comision comision, Long materiaId);

    Comision recuperar(Long comisionId);

    List<List<Comision>>  encontrarIdsNCombinacionCompatible(List<Long> materiasIds, Integer cantidadHorarios);

    List<Comision> recuperarPorIds(List<Long> comisionIds);

    void validarSuperPosicion(List<Long> comisionIds, List<Long> comisionesAnotadas);

    boolean hayComisionesDeMismaMateriaEnNuevas(List<Long> comisionIds);

    List<Materia> recuperarMateriasPorComision(List<Long> comisionIds);

    Page<Comision> recuperarComisiones(int page, Long alumnoId);
}
