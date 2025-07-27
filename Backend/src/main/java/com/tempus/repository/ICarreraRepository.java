package com.tempus.repository;

import com.tempus.models.Carrera;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICarreraRepository extends JpaRepository<Carrera, Long> {
}
