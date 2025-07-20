package com.Tempus.Repository;

import com.Tempus.DTO.MateriaDTO;
import com.Tempus.DTO.MateriaResumenDTO;
import com.Tempus.Models.Materia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface IMateriaRepository extends JpaRepository<Materia, Long> {
    @Query("SELECT new com.Tempus.DTO.MateriaResumenDTO(m.id, m.nombre) FROM Materia m WHERE m.carrera.id_carrera = :idCarrera")
    Set<MateriaResumenDTO> buscarMateriasPorCarrera(@Param("idCarrera") Long idCarrera);
}
