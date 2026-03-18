package edu.ar.tempus.persistence.sql;

import edu.ar.tempus.model.ClaseHorario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClaseHorarioSQLDAO extends JpaRepository<ClaseHorario,Long> {
}
