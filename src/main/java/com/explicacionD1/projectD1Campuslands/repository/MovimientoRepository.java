package com.explicacionD1.projectD1Campuslands.repository;

import com.explicacionD1.projectD1Campuslands.model.Movimiento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovimientoRepository extends JpaRepository<Movimiento, Long> {
}