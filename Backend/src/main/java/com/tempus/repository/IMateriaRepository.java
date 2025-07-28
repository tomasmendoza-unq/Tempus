package com.tempus.repository;

import com.tempus.models.Materia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IMateriaRepository extends JpaRepository<Materia, Long> {

    @Query("SELECT m FROM Materia m WHERE m.carrera.id = :idCarrera")
    List<Materia> findMateriasByCarreraId(@Param("idCarrera") Long idCarrera);

}
