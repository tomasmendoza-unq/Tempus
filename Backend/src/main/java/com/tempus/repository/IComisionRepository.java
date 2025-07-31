package com.tempus.repository;

import com.tempus.models.Comision;
import com.tempus.models.Materia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface IComisionRepository extends JpaRepository<Comision, Long> {

    @Query("SELECT c FROM Comision c LEFT JOIN FETCH c.materia WHERE c.idComision = :id")
    Optional<Comision> findComisionWithMateria(@Param("id") Long id);

    @Query("SELECT c FROM Comision c JOIN FETCH c.materia m")
    List<Comision> findAllWithMateria();
}
