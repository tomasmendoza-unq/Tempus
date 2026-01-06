package edu.ar.tempus.persistence.sql;

import edu.ar.tempus.model.Carrera;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarreraDAOSQL extends JpaRepository<Carrera,Long> {
}
