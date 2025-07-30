package com.tempus.repository;

import com.tempus.models.Comision;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IComisionRepository extends JpaRepository<Comision, Long> {
}
