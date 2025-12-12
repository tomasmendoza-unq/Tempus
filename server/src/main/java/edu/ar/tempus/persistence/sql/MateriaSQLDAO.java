package edu.ar.tempus.persistence.sql;

import edu.ar.tempus.model.Materia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MateriaSQLDAO extends JpaRepository<Materia,Long> {
}
