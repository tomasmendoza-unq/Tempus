package com.Tempus.Repository;

import com.Tempus.Models.Carrera;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICarreraRepository extends JpaRepository<Carrera, Long> {
}
