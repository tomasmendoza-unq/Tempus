package edu.ar.tempus.persistence.repository;

import edu.ar.tempus.model.Comision;
import edu.ar.tempus.model.Materia;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ComisionRepository {
    Comision guardar(Comision comision);

    Comision recuperar(Long comisionId);

    List<List<Comision>> encontrarCombinacionCompatible(List<Long> materiasIds, Integer cantidadHorarios);

    List<Comision> findAll(List<Long> comisionesCompatibles);

    boolean haySuperposicionHoraria(List<Long> comisionIds, List<Long> comisionesAnotadas);

    boolean hayComisionesDeMismaMateriaEnNuevas(List<Long> comisionIds);

    List<Materia> recuperarMateriasPorComision(List<Long> comisionIds);

    Page<Comision> recuperarComisiones(Pageable pageable, Long carreraIds);

    void delete(Long idComision);
}
