package com.Tempus.Repository;

import com.Tempus.Models.Materia;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IMateriaRepository extends JpaRepository<Materia, Long> {
}
