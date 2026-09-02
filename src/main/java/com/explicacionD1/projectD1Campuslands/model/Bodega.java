package com.explicacionD1.projectD1Campuslands.model;

import com.explicacionD1.projectD1Campuslands.audit.AuditoriaListener;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@EntityListeners(AuditoriaListener.class) // <--- Activa la auditoría para Bodega
public class Bodega {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String ubicacion;
    private Integer capacidad;
    private String encargado;
}