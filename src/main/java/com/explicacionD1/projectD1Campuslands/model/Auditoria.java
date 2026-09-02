package com.explicacionD1.projectD1Campuslands.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Data
public class Auditoria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String tipoOperacion;
    private LocalDateTime fechaHora;
    private String entidadAfectada;
    private String usuario;

    // Usamos TEXT porque el .toString() de la entidad puede ser muy largo
    @Column(columnDefinition = "TEXT")
    private String valoresAnteriores;

    @Column(columnDefinition = "TEXT")
    private String valoresNuevos;
}