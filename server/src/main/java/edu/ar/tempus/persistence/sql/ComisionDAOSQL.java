package edu.ar.tempus.persistence.sql;

import edu.ar.tempus.model.Comision;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ComisionDAOSQL extends JpaRepository<Comision,Long> {
    List<Comision> findAllByMateria_MateriaId(Long materiaMateriaId);
}
