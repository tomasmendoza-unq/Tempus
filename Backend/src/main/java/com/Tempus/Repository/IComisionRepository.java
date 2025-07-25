package com.Tempus.Repository;

import com.Tempus.Models.Comision;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IComisionRepository extends JpaRepository<Comision, Long> {
}
