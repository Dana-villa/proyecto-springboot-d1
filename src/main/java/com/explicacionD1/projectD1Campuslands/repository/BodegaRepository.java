package com.explicacionD1.projectD1Campuslands.repository;

import com.explicacionD1.projectD1Campuslands.model.Bodega;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BodegaRepository extends JpaRepository<Bodega, Long> {
    // JpaRepository ya incluye findAll(), save(), findById(), deleteById(), etc.
}