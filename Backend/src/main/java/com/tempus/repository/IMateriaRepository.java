package com.tempus.repository;

import com.tempus.models.Materia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface IMateriaRepository extends JpaRepository<Materia, Long> {

    @Query("SELECT m FROM Materia m WHERE m.carrera.id = :idCarrera")
    List<Materia> findMateriasByCarreraId(@Param("idCarrera") Long idCarrera);

    @Query("SELECT m FROM Materia m LEFT JOIN FETCH m.correlativas WHERE m.idMateria = :id")
    Optional<Materia> findWithCorrelativasById(@Param("id") Long id);


}
