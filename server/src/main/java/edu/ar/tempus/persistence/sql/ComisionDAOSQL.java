package edu.ar.tempus.persistence.sql;

import edu.ar.tempus.model.Comision;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ComisionDAOSQL extends JpaRepository<Comision,Long> {
}
