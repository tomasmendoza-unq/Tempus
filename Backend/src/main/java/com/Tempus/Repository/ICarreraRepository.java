package com.Tempus.Repository;

import com.Tempus.Models.Carrera;
import com.Tempus.Models.Materia;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface ICarreraRepository extends JpaRepository<Carrera, Long> {

}
